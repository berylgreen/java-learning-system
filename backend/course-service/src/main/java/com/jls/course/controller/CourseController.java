package com.jls.course.controller;

import com.jls.course.dto.CourseStructureDTO;
import com.jls.course.dto.LessonDetailDTO;
import com.jls.course.model.Course;
import com.jls.course.repository.CourseRepository;
import com.jls.course.service.CourseService;
import com.jls.course.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    private final CourseRepository courseRepository;
    private final ImportService importService;
    private final CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/structure")
    public ResponseEntity<CourseStructureDTO> getCourseStructure(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseStructure(id));
    }

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<LessonDetailDTO> getLessonDetail(@PathVariable Long lessonId) {
        return ResponseEntity.ok(courseService.getLessonDetail(lessonId));
    }

    @PostMapping("/import")
    public ResponseEntity<Course> importCourse(@RequestBody String markdown) {
        Course course = importService.importCourse(markdown);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/import/source-index")
    public ResponseEntity<Course> importCourseFromSourceIndex(@RequestBody String sourceIndexJson) {
        Course course = importService.importCourseFromSourceIndexJson(sourceIndexJson);
        return ResponseEntity.ok(course);
    }
}
