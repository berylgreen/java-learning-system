package com.jls.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {
    private Long id;
    private Long lessonId;
    private String title;
    private String description;
    private String starterCode;
    private List<ExerciseTestCaseDTO> publicTestCases;
}
