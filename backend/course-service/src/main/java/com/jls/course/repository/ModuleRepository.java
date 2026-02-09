package com.jls.course.repository;

import com.jls.course.model.Course;
import com.jls.course.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourseIdOrderByOrderIndexAsc(Long courseId);
    List<Module> findByCourse(Course course);
}
