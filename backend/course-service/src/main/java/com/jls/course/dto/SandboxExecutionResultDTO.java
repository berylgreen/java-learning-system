package com.jls.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SandboxExecutionResultDTO {
    private String output;
    private String error;
    private String value;
    private boolean success;
}
