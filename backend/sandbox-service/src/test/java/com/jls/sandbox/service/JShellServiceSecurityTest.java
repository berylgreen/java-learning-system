package com.jls.sandbox.service;

import com.jls.sandbox.model.ExecutionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JShellServiceSecurityTest {

    @Autowired
    private JShellService jShellService;

    @Test
    void testSystemExit() {
        String code = "System.exit(0);";
        ExecutionResult result = jShellService.execute(code);

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertTrue(result.getError().toLowerCase().contains("forbidden") ||
                   result.getError().toLowerCase().contains("not allowed"),
                   "Should mention forbidden or not allowed. Found: " + result.getError());
    }

    @Test
    void testFileAccess() {
        String code = "new java.io.File(\"test.txt\").exists();";
        ExecutionResult result = jShellService.execute(code);

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertTrue(result.getError().toLowerCase().contains("forbidden") ||
                   result.getError().toLowerCase().contains("not allowed"),
                   "Should mention forbidden or not allowed. Found: " + result.getError());
    }

    @Test
    void testRuntimeExec() {
        String code = "Runtime.getRuntime().exec(\"calc\");";
        ExecutionResult result = jShellService.execute(code);

        assertNotNull(result);
        assertFalse(result.isSuccess());
        assertTrue(result.getError().toLowerCase().contains("forbidden") ||
                   result.getError().toLowerCase().contains("not allowed"),
                   "Should mention forbidden or not allowed. Found: " + result.getError());
    }
}
