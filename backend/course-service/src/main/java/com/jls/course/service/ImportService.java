package com.jls.course.service;

import com.jls.course.model.Course;
import com.jls.course.model.Lesson;
import com.jls.course.model.Module;
import com.jls.course.repository.CourseRepository;
import com.jls.course.repository.LessonRepository;
import com.jls.course.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

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
}
