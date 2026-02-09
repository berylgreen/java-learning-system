package com.jls.course.repository;

import com.jls.course.model.Lesson;
import com.jls.course.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByModuleId(Long moduleId);
    List<Lesson> findByModule(Module module);
}
