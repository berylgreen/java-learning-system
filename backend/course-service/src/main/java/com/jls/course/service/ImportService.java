package com.jls.course.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jls.course.dto.ExerciseTestCaseDTO;
import com.jls.course.model.Course;
import com.jls.course.model.Exercise;
import com.jls.course.model.Lesson;
import com.jls.course.model.Module;
import com.jls.course.repository.CourseRepository;
import com.jls.course.repository.ExerciseRepository;
import com.jls.course.repository.LessonRepository;
import com.jls.course.repository.ModuleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ImportService {

    private static final String EXERCISE_CONFIG_SECTION_TITLE = "## 课后习题配置（导入用）";
    private static final Pattern LESSON_INDEX_PATTERN = Pattern.compile("^(\\d+)(?:\\.\\d+)?");
    private static final Pattern EXERCISE_MARKER_PATTERN = Pattern.compile("^\\s*-\\s*(.+?)\\s*[：:]\\s*(.+)$");
    private static final Pattern EXERCISE_HINT_PATTERN = Pattern.compile("^\\s*\\*\\s*提示\\s*[：:]\\s*(.+)$");
    private static final Pattern EXERCISE_ANSWER_PATTERN = Pattern.compile("^\\s*\\*\\s*答案\\s*[：:]\\s*(.+)$");
    private static final Pattern EXERCISE_CASE_PATTERN = Pattern.compile("^\\s*\\*\\s*(公开|隐藏)\\s*\\|\\s*输入\\s*[：:]\\s*(.*?)\\s*\\|\\s*输出\\s*[：:]\\s*(.*?)\\s*$");

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final ExerciseRepository exerciseRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public Course importCourse(String markdownContent) {
        String[] lines = markdownContent.split("\n");
        Map<String, ExerciseConfig> configuredExerciseMap = parseExerciseConfig(markdownContent);
        Course currentCourse = null;
        Module currentModule = null;
        Lesson currentLesson = null;
        StringBuilder contentBuilder = new StringBuilder();
        StringBuilder codeBuilder = new StringBuilder();
        boolean inCodeBlock = false;
        boolean inExerciseConfigSection = false;
        int moduleOrder = 0;

        for (String line : lines) {
            if (line.startsWith("## ")) {
                inExerciseConfigSection = EXERCISE_CONFIG_SECTION_TITLE.equals(line.trim());
            }

            if (inExerciseConfigSection) {
                continue;
            }

            if (line.startsWith("```java")) {
                inCodeBlock = true;
                continue;
            } else if (line.startsWith("```") && inCodeBlock) {
                inCodeBlock = false;
                if (currentLesson != null) {
                    currentLesson.setCodeSnippet(codeBuilder.toString().trim());
                    codeBuilder = new StringBuilder();
                }
                continue;
            }

            if (inCodeBlock) {
                codeBuilder.append(line).append("\n");
                continue;
            }

            if (line.startsWith("# ")) {
                currentCourse = Course.builder()
                        .title(line.substring(2).trim())
                        .build();
                currentCourse = courseRepository.save(currentCourse);
            } else if (line.startsWith("## ")) {
                if (currentCourse == null) {
                    throw new IllegalStateException("Module found without a course title (#)");
                }
                currentModule = Module.builder()
                        .title(line.substring(3).trim())
                        .course(currentCourse)
                        .orderIndex(++moduleOrder)
                        .build();
                currentModule = moduleRepository.save(currentModule);
            } else if (line.startsWith("### ")) {
                if (currentModule == null) {
                    throw new IllegalStateException("Lesson found without a module title (##)");
                }
                if (currentLesson != null) {
                    currentLesson.setContent(contentBuilder.toString().trim());
                    lessonRepository.save(currentLesson);
                    contentBuilder = new StringBuilder();
                }
                currentLesson = Lesson.builder()
                        .title(line.substring(4).trim())
                        .module(currentModule)
                        .build();
                currentLesson = lessonRepository.save(currentLesson);
                upsertExerciseForLesson(currentLesson, configuredExerciseMap.get(currentLesson.getTitle()));
            } else {
                if (currentLesson != null) {
                    contentBuilder.append(line).append("\n");
                }
            }
        }

        if (currentLesson != null) {
            currentLesson.setContent(contentBuilder.toString().trim());
            lessonRepository.save(currentLesson);
        }

        return currentCourse;
    }

    private Map<String, ExerciseConfig> parseExerciseConfig(String markdownContent) {
        String[] lines = markdownContent.split("\n");
        Map<String, ExerciseConfig> result = new LinkedHashMap<>();
        boolean inConfigSection = false;
        String currentLessonTitle = null;

        for (String line : lines) {
            if (line.startsWith("## ")) {
                if (EXERCISE_CONFIG_SECTION_TITLE.equals(line.trim())) {
                    inConfigSection = true;
                    continue;
                }
                if (inConfigSection) {
                    break;
                }
            }

            if (!inConfigSection) {
                continue;
            }

            Matcher exerciseMatcher = EXERCISE_MARKER_PATTERN.matcher(line.trim());
            if (exerciseMatcher.matches()) {
                String lessonTitle = exerciseMatcher.group(1).trim();
                String exerciseText = exerciseMatcher.group(2).trim();
                if (!lessonTitle.isBlank()) {
                    ExerciseConfig config = result.computeIfAbsent(lessonTitle, key -> new ExerciseConfig());
                    config.setDescription(exerciseText);
                    currentLessonTitle = lessonTitle;
                }
                continue;
            }

            Matcher hintMatcher = EXERCISE_HINT_PATTERN.matcher(line.trim());
            if (hintMatcher.matches() && currentLessonTitle != null) {
                ExerciseConfig config = result.get(currentLessonTitle);
                if (config != null) {
                    config.setHint(hintMatcher.group(1).trim());
                }
                continue;
            }

            Matcher answerMatcher = EXERCISE_ANSWER_PATTERN.matcher(line.trim());
            if (answerMatcher.matches() && currentLessonTitle != null) {
                ExerciseConfig config = result.get(currentLessonTitle);
                if (config != null) {
                    config.setAnswerCode(unescapeMultilineValue(answerMatcher.group(1).trim()));
                }
                continue;
            }

            Matcher caseMatcher = EXERCISE_CASE_PATTERN.matcher(line.trim());
            if (caseMatcher.matches() && currentLessonTitle != null) {
                ExerciseConfig config = result.get(currentLessonTitle);
                if (config == null) {
                    continue;
                }

                ExerciseTestCaseDTO testCase = ExerciseTestCaseDTO.builder()
                        .input(normalizeCaseValue(caseMatcher.group(2)))
                        .expectedOutput(normalizeCaseValue(caseMatcher.group(3)))
                        .timeoutMs(2000L)
                        .build();

                if ("公开".equals(caseMatcher.group(1))) {
                    config.getPublicCases().add(testCase);
                } else {
                    config.getHiddenCases().add(testCase);
                }
            }
        }

        return result;
    }

    private String normalizeCaseValue(String value) {
        if (value == null) {
            return "";
        }
        String normalized = value.trim();
        return "(空)".equals(normalized) ? "" : normalized;
    }

    private String unescapeMultilineValue(String value) {
        if (value == null) {
            return null;
        }
        return value.replace("\\n", "\n");
    }

    private void upsertExerciseForLesson(Lesson lesson) {
        upsertExerciseForLesson(lesson, null);
    }

    private void upsertExerciseForLesson(Lesson lesson, ExerciseConfig configuredExercise) {
        String finalDescription = configuredExercise == null || configuredExercise.getDescription() == null
                || configuredExercise.getDescription().isBlank()
                ? buildExerciseDescription(lesson.getTitle())
                : configuredExercise.getDescription();

        String finalHint = configuredExercise == null || configuredExercise.getHint() == null
                || configuredExercise.getHint().isBlank()
                ? buildDefaultHint(lesson.getTitle())
                : configuredExercise.getHint();

        String finalAnswerCode = configuredExercise == null || configuredExercise.getAnswerCode() == null
                || configuredExercise.getAnswerCode().isBlank()
                ? null
                : configuredExercise.getAnswerCode();

        String publicCasesJson = resolvePublicCasesJson(lesson.getTitle(), configuredExercise);
        String hiddenCasesJson = resolveHiddenCasesJson(lesson.getTitle(), configuredExercise);

        exerciseRepository.findByLesson(lesson).ifPresentOrElse(existing -> {
            existing.setTitle(buildExerciseTitle(lesson.getTitle()));
            existing.setDescription(finalDescription);
            existing.setStarterCode(buildStarterCode(lesson.getTitle()));
            existing.setAnswerHint(finalHint);
            existing.setAnswerCode(finalAnswerCode);
            existing.setPublicTestCasesJson(publicCasesJson);
            existing.setHiddenTestCasesJson(hiddenCasesJson);
            existing.setPassRule("ALL_PASS");
            exerciseRepository.save(existing);
        }, () -> {
            Exercise exercise = Exercise.builder()
                    .lesson(lesson)
                    .title(buildExerciseTitle(lesson.getTitle()))
                    .description(finalDescription)
                    .starterCode(buildStarterCode(lesson.getTitle()))
                    .answerHint(finalHint)
                    .answerCode(finalAnswerCode)
                    .publicTestCasesJson(publicCasesJson)
                    .hiddenTestCasesJson(hiddenCasesJson)
                    .passRule("ALL_PASS")
                    .build();
            exerciseRepository.save(exercise);
        });
    }

    private String resolvePublicCasesJson(String lessonTitle, ExerciseConfig configuredExercise) {
        if (configuredExercise != null && !configuredExercise.getPublicCases().isEmpty()) {
            return writeAsJson(configuredExercise.getPublicCases());
        }
        return buildPublicCasesJson(lessonTitle);
    }

    private String resolveHiddenCasesJson(String lessonTitle, ExerciseConfig configuredExercise) {
        if (configuredExercise != null && !configuredExercise.getHiddenCases().isEmpty()) {
            return writeAsJson(configuredExercise.getHiddenCases());
        }
        return buildHiddenCasesJson(lessonTitle);
    }

    private String buildExerciseTitle(String lessonTitle) {
        return lessonTitle + " - 练习";
    }

    private String buildExerciseDescription(String lessonTitle) {
        return "请实现函数 solution(String input)，完成“" + lessonTitle + "”对应知识点练习。";
    }

    private String buildDefaultHint(String lessonTitle) {
        return "先梳理输入输出格式，再按题目要求分步骤实现 solution。";
    }

    private String buildStarterCode(String lessonTitle) {
        int chapter = extractChapterIndex(lessonTitle);
        switch (chapter) {
            case 1:
                return "String solution(String input){\n" +
                        "    return \"Hello, \" + input;\n" +
                        "}";
            case 2:
                return "String solution(String input){\n" +
                        "    return input == null ? \"\" : input.trim().toUpperCase();\n" +
                        "}";
            case 3:
                return "String solution(String input){\n" +
                        "    if (input == null || input.isBlank()) return \"0\";\n" +
                        "    String[] parts = input.split(\",\");\n" +
                        "    int sum = 0;\n" +
                        "    for (String p : parts) sum += Integer.parseInt(p.trim());\n" +
                        "    return String.valueOf(sum);\n" +
                        "}";
            case 4:
                return "String solution(String input){\n" +
                        "    return input == null ? \"\" : input.replace(\" \", \"\");\n" +
                        "}";
            case 5:
                return "String solution(String input){\n" +
                        "    return input == null ? \"\" : input.toLowerCase();\n" +
                        "}";
            case 6:
                return "String solution(String input){\n" +
                        "    try {\n" +
                        "        Integer.parseInt(input);\n" +
                        "        return \"OK\";\n" +
                        "    } catch (Exception e) {\n" +
                        "        return \"ERROR\";\n" +
                        "    }\n" +
                        "}";
            case 7:
                return "String solution(String input){\n" +
                        "    if (input == null || input.isBlank()) return \"0\";\n" +
                        "    return String.valueOf(input.split(\",\").length);\n" +
                        "}";
            case 8:
                return "String solution(String input){\n" +
                        "    return input == null ? \"\" : input.replace(\".\", \"/\");\n" +
                        "}";
            case 9:
                return "String solution(String input){\n" +
                        "    if (input == null || input.isBlank()) return \"0\";\n" +
                        "    int n = Integer.parseInt(input.trim());\n" +
                        "    int sum = 0;\n" +
                        "    for (int i = 1; i <= n; i++) sum += i;\n" +
                        "    return String.valueOf(sum);\n" +
                        "}";
            default:
                return "String solution(String input){\n" +
                        "    return input == null ? \"\" : input;\n" +
                        "}";
        }
    }

    private String buildPublicCasesJson(String lessonTitle) {
        int chapter = extractChapterIndex(lessonTitle);
        List<ExerciseTestCaseDTO> cases;
        switch (chapter) {
            case 1:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("Java").expectedOutput("Hello, Java").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("World").expectedOutput("Hello, World").timeoutMs(2000L).build()
                );
                break;
            case 2:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("  java  ").expectedOutput("JAVA").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("AbC").expectedOutput("ABC").timeoutMs(2000L).build()
                );
                break;
            case 3:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("1,2,3").expectedOutput("6").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("10,20").expectedOutput("30").timeoutMs(2000L).build()
                );
                break;
            case 4:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("a b c").expectedOutput("abc").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input(" j a v a ").expectedOutput("java").timeoutMs(2000L).build()
                );
                break;
            case 5:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("JAVA").expectedOutput("java").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("LanGuAge").expectedOutput("language").timeoutMs(2000L).build()
                );
                break;
            case 6:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("123").expectedOutput("OK").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("abc").expectedOutput("ERROR").timeoutMs(2000L).build()
                );
                break;
            case 7:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("a,b,c").expectedOutput("3").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("one").expectedOutput("1").timeoutMs(2000L).build()
                );
                break;
            case 8:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("2026.02.14").expectedOutput("2026/02/14").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("java.io").expectedOutput("java/io").timeoutMs(2000L).build()
                );
                break;
            case 9:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("3").expectedOutput("6").timeoutMs(2000L).build(),
                        ExerciseTestCaseDTO.builder().input("5").expectedOutput("15").timeoutMs(2000L).build()
                );
                break;
            default:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("abc").expectedOutput("abc").timeoutMs(2000L).build()
                );
                break;
        }
        return writeAsJson(cases);
    }

    private String buildHiddenCasesJson(String lessonTitle) {
        int chapter = extractChapterIndex(lessonTitle);
        List<ExerciseTestCaseDTO> cases;
        switch (chapter) {
            case 1:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("Student").expectedOutput("Hello, Student").timeoutMs(2000L).build()
                );
                break;
            case 2:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("  Type  ").expectedOutput("TYPE").timeoutMs(2000L).build()
                );
                break;
            case 3:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("7,8,9").expectedOutput("24").timeoutMs(2000L).build()
                );
                break;
            case 4:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("a  b").expectedOutput("ab").timeoutMs(2000L).build()
                );
                break;
            case 5:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("MiXeD").expectedOutput("mixed").timeoutMs(2000L).build()
                );
                break;
            case 6:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("-77").expectedOutput("OK").timeoutMs(2000L).build()
                );
                break;
            case 7:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("x,y").expectedOutput("2").timeoutMs(2000L).build()
                );
                break;
            case 8:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("a.b.c").expectedOutput("a/b/c").timeoutMs(2000L).build()
                );
                break;
            case 9:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("10").expectedOutput("55").timeoutMs(2000L).build()
                );
                break;
            default:
                cases = List.of(
                        ExerciseTestCaseDTO.builder().input("xyz").expectedOutput("xyz").timeoutMs(2000L).build()
                );
                break;
        }
        return writeAsJson(cases);
    }

    private int extractChapterIndex(String lessonTitle) {
        if (lessonTitle == null) {
            return -1;
        }
        Matcher matcher = LESSON_INDEX_PATTERN.matcher(lessonTitle.trim());
        if (!matcher.find()) {
            return -1;
        }
        try {
            return Integer.parseInt(matcher.group(1));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String writeAsJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("练习用例 JSON 序列化失败", e);
        }
    }

    @Transactional
    public Course importCourseFromSourceIndexFile(String indexFilePath) {
        try {
            String json = Files.readString(Paths.get(indexFilePath));
            return importCourseFromSourceIndexJson(json);
        } catch (IOException e) {
            throw new IllegalStateException("读取源码索引文件失败: " + indexFilePath, e);
        }
    }

    @Transactional
    public Course importCourseFromSourceIndexJson(String sourceIndexJson) {
        SourceIndex sourceIndex;
        try {
            sourceIndex = objectMapper.readValue(sourceIndexJson, SourceIndex.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("源码索引 JSON 解析失败", e);
        }

        if (sourceIndex == null || sourceIndex.getCourseTitle() == null || sourceIndex.getCourseTitle().isBlank()) {
            throw new IllegalStateException("源码索引缺少课程标题");
        }

        Course course = courseRepository.save(Course.builder()
                .title(sourceIndex.getCourseTitle())
                .description("由源码索引自动导入")
                .build());

        Path sourceRootPath = resolveSourceRootPath(sourceIndex.getSourceRoot());

        List<SourceChapter> chapters = sourceIndex.getChapters() == null ? Collections.emptyList() : sourceIndex.getChapters();
        int moduleOrder = 0;

        for (SourceChapter chapter : chapters) {
            Module module = moduleRepository.save(Module.builder()
                    .title(chapter.getChapterName())
                    .course(course)
                    .orderIndex(++moduleOrder)
                    .build());

            List<String> files = chapter.getFiles() == null ? Collections.emptyList() : chapter.getFiles();
            for (String relativeFilePath : files) {
                String sourceCode = readSourceCode(sourceRootPath, chapter.getChapterName(), relativeFilePath);
                String lessonTitle = buildLessonTitle(relativeFilePath);
                String lessonContent = "章节: " + chapter.getChapterName() + "\n源码路径: "
                        + chapter.getChapterName() + "/" + relativeFilePath;

                Lesson lesson = lessonRepository.save(Lesson.builder()
                        .title(lessonTitle)
                        .content(lessonContent)
                        .codeSnippet(sourceCode)
                        .module(module)
                        .build());
                upsertExerciseForLesson(lesson);
            }
        }

        return course;
    }

    private Path resolveSourceRootPath(String sourceRoot) {
        String safeSourceRoot = sourceRoot == null ? "" : sourceRoot;
        Path rawPath = Paths.get(safeSourceRoot);
        if (rawPath.isAbsolute()) {
            return rawPath.normalize();
        }

        Path fromProjectRoot = Paths.get("").toAbsolutePath().resolve(rawPath).normalize();
        if (Files.exists(fromProjectRoot)) {
            return fromProjectRoot;
        }

        Path fromModuleRoot = Paths.get("").toAbsolutePath().resolve("..").resolve("..").resolve(rawPath).normalize();
        return fromModuleRoot;
    }

    private String buildLessonTitle(String relativeFilePath) {
        Path path = Paths.get(relativeFilePath);
        String fileName = path.getFileName() == null ? relativeFilePath : path.getFileName().toString();
        if (fileName.endsWith(".java")) {
            return fileName.substring(0, fileName.length() - 5);
        }
        return fileName;
    }

    private String readSourceCode(Path sourceRootPath, String chapterName, String relativeFilePath) {
        Path sourceFilePath = sourceRootPath.resolve(chapterName).resolve(relativeFilePath).normalize();
        if (!Files.exists(sourceFilePath)) {
            return "// 未找到源码文件: " + sourceFilePath;
        }

        try {
            return Files.readString(sourceFilePath);
        } catch (IOException e) {
            return "// 读取源码文件失败: " + sourceFilePath;
        }
    }

    @Data
    private static class ExerciseConfig {
        private String description;
        private String hint;
        private String answerCode;
        private List<ExerciseTestCaseDTO> publicCases = new ArrayList<>();
        private List<ExerciseTestCaseDTO> hiddenCases = new ArrayList<>();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class SourceIndex {
        private String courseTitle;
        private String sourceRoot;
        private String generatedAt;
        private List<SourceChapter> chapters;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class SourceChapter {
        private String chapterName;
        private Integer javaFileCount;
        private List<String> files;
    }
}
