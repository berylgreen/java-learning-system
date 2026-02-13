package com.jls.course.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jls.course.model.Course;
import com.jls.course.model.Lesson;
import com.jls.course.model.Module;
import com.jls.course.repository.CourseRepository;
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
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public Course importCourse(String markdownContent) {
        String[] lines = markdownContent.split("\n");
        Course currentCourse = null;
        Module currentModule = null;
        Lesson currentLesson = null;
        StringBuilder contentBuilder = new StringBuilder();
        StringBuilder codeBuilder = new StringBuilder();
        boolean inCodeBlock = false;
        int moduleOrder = 0;

        for (String line : lines) {
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

                lessonRepository.save(Lesson.builder()
                        .title(lessonTitle)
                        .content(lessonContent)
                        .codeSnippet(sourceCode)
                        .module(module)
                        .build());
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
