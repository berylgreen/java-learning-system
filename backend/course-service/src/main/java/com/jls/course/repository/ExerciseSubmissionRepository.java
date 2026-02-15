package com.jls.course.repository;

import com.jls.course.model.Exercise;
import com.jls.course.model.ExerciseSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseSubmissionRepository extends JpaRepository<ExerciseSubmission, Long> {
    Optional<ExerciseSubmission> findTopByExerciseIdOrderByCreatedAtDesc(Long exerciseId);

    Optional<ExerciseSubmission> findTopByExerciseOrderByCreatedAtDesc(Exercise exercise);

    List<ExerciseSubmission> findByExercise(Exercise exercise);

    List<ExerciseSubmission> findByExerciseIn(List<Exercise> exercises);
}
