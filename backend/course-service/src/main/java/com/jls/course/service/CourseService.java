package com.jls.course.service;

import com.jls.course.dto.CourseStructureDTO;
import com.jls.course.dto.LessonDTO;
import com.jls.course.dto.LessonDetailDTO;
import com.jls.course.dto.ModuleDTO;
import com.jls.course.model.Course;
import com.jls.course.model.Lesson;
import com.jls.course.model.Module;
import com.jls.course.repository.CourseRepository;
import com.jls.course.repository.LessonRepository;
import com.jls.course.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    @Transactional(readOnly = true)
    public CourseStructureDTO getCourseStructure(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        List<Module> modules = moduleRepository.findByCourseIdOrderByOrderIndexAsc(courseId);

        List<ModuleDTO> moduleDTOs = modules.stream().map(module -> {
            List<Lesson> lessons = lessonRepository.findByModuleIdOrderByIdAsc(module.getId());
            List<LessonDTO> lessonDTOs = lessons.stream().map(lesson ->
                LessonDTO.builder()
                        .id(lesson.getId())
                        .title(lesson.getTitle())
                        .build()
            ).collect(Collectors.toList());

            return ModuleDTO.builder()
                    .id(module.getId())
                    .title(module.getTitle())
                    .orderIndex(module.getOrderIndex())
                    .lessons(lessonDTOs)
                    .build();
        }).collect(Collectors.toList());

        return CourseStructureDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .modules(moduleDTOs)
                .build();
    }

    @Transactional(readOnly = true)
    public LessonDetailDTO getLessonDetail(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + lessonId));

        return LessonDetailDTO.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .codeSnippet(lesson.getCodeSnippet())
                .build();
    }
}
