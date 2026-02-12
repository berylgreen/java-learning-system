package com.jls.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDetailDTO {
    private Long id;
    private String title;
    private String content;
    private String codeSnippet;
}
