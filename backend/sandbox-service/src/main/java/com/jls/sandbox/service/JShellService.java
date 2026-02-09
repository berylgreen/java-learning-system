package com.jls.sandbox.service;

import com.jls.sandbox.model.ExecutionResult;
import jdk.jshell.JShell;
import jdk.jshell.Snippet;
import jdk.jshell.SnippetEvent;
import jdk.jshell.SourceCodeAnalysis;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class JShellService {

    private static final long DEFAULT_TIMEOUT_SECONDS = 5;

    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile("class\\s+(\\w+)\\s*\\{");
    private static final Pattern MAIN_METHOD_PATTERN = Pattern.compile("public\\s+static\\s+void\\s+main");

    private static final List<String> FORBIDDEN_PATTERNS = List.of(
            "System.exit",
            "Runtime.getRuntime",
            "java.io.File",
            "java.nio.file",
            "java.net",
            "ProcessBuilder",
            "ClassLoader"
    );

    public ExecutionResult execute(String code) {
        String securityCheckError = checkSecurity(code);
        if (securityCheckError != null) {
            return ExecutionResult.builder()
                    .output("")
                    .error(securityCheckError)
                    .success(false)
                    .build();
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<ExecutionResult> future = executor.submit(() -> {
                try (JShell jshell = JShell.builder()
                        .out(new PrintStream(out))
                        .err(new PrintStream(err))
                        .build()) {

                    List<SnippetEvent> allEvents = new ArrayList<>();
                    SourceCodeAnalysis analysis = jshell.sourceCodeAnalysis();
                    String remaining = code;
                    while (!remaining.isEmpty()) {
                        SourceCodeAnalysis.CompletionInfo info = analysis.analyzeCompletion(remaining);
                        if (!info.completeness().isComplete()) {
                            break;
                        }
                        allEvents.addAll(jshell.eval(info.source()));
                        remaining = info.remaining();
                    }

                    String lastValue = "";
                    boolean success = true;

                    for (SnippetEvent event : allEvents) {
                        if (event.status() == Snippet.Status.VALID) {
                            if (event.value() != null) {
                                lastValue = event.value();
                            }
                        } else if (event.status() == Snippet.Status.REJECTED ||
                                   event.status() == Snippet.Status.DROPPED) {
                            success = false;
                            jshell.diagnostics(event.snippet()).forEach(d -> {
                                String msg = String.format("Error: %s\n", d.getMessage(null));
                                try {
                                    err.write(msg.getBytes());
                                } catch (Exception ignored) {}
                            });
                        }

                        if (event.exception() != null) {
                            success = false;
                            String msg = String.format("Exception: %s\n", event.exception().getMessage());
                            try {
                                err.write(msg.getBytes());
                            } catch (Exception ignored) {}
                        }
                    }

                    if (success) {
                        Matcher nameMatcher = CLASS_NAME_PATTERN.matcher(code);
                        Matcher mainMatcher = MAIN_METHOD_PATTERN.matcher(code);
                        if (nameMatcher.find() && mainMatcher.find()) {
                            String className = nameMatcher.group(1);
                            jshell.eval(className + ".main(new String[0]);");
                        }
                    }

                    return ExecutionResult.builder()
                            .output(out.toString())
                            .error(err.toString())
                            .value(lastValue)
                            .success(success)
                            .build();
                }
            });

            return future.get(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            return ExecutionResult.builder()
                    .output(out.toString())
                    .error("Execution timed out after " + DEFAULT_TIMEOUT_SECONDS + " seconds")
                    .success(false)
                    .build();
        } catch (Exception e) {
            return ExecutionResult.builder()
                    .output(out.toString())
                    .error(err.toString() + "\n" + e.getMessage())
                    .success(false)
                    .build();
        } finally {
            executor.shutdownNow();
        }
    }

    private String checkSecurity(String code) {
        for (String pattern : FORBIDDEN_PATTERNS) {
            if (code.contains(pattern)) {
                return "Operation not allowed: " + pattern + " is forbidden";
            }
        }
        return null;
    }
}
