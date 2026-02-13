package com.jls.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseSubmissionResultDTO {
    private Long submissionId;
    private String status;
    private Integer score;
    private String message;
    private List<ExerciseCaseResultDTO> publicResults;
    private ExerciseHiddenSummaryDTO hiddenSummary;
    private Long durationMs;
    private LocalDateTime createdAt;
}
