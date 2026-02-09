package com.jls.course.service;

import com.jls.course.model.Course;
import com.jls.course.model.Lesson;
import com.jls.course.model.Module;
import com.jls.course.repository.CourseRepository;
import com.jls.course.repository.LessonRepository;
import com.jls.course.repository.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ImportServiceTest {

    @Autowired
    private ImportService importService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Test
    void testImportSimpleCourse() {
        String markdown = "# Java Basics\n" +
                "## Introduction\n" +
                "### Hello World\n" +
                "Welcome to Java.\n" +
                "```java\n" +
                "System.out.println(\"Hello\");\n" +
                "```";

        Course course = importService.importCourse(markdown);

        assertNotNull(course.getId());
        assertEquals("Java Basics", course.getTitle());

        List<com.jls.course.model.Module> modules = moduleRepository.findByCourse(course);
        assertEquals(1, modules.size());
        assertEquals("Introduction", modules.get(0).getTitle());

        List<Lesson> lessons = lessonRepository.findByModule(modules.get(0));
        assertEquals(1, lessons.size());
        assertEquals("Hello World", lessons.get(0).getTitle());
        assertTrue(lessons.get(0).getContent().contains("Welcome to Java."));
        assertEquals("System.out.println(\"Hello\");", lessons.get(0).getCodeSnippet().trim());
    }
}
