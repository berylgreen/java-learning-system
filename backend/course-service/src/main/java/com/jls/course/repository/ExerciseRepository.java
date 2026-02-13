package com.jls.course.repository;

import com.jls.course.model.Exercise;
import com.jls.course.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByLessonId(Long lessonId);

    Optional<Exercise> findByLesson(Lesson lesson);

    List<Exercise> findByLessonIn(List<Lesson> lessons);
}
