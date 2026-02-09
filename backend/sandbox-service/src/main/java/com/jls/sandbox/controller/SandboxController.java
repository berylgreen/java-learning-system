package com.jls.sandbox.controller;

import com.jls.sandbox.model.ExecutionRequest;
import com.jls.sandbox.model.ExecutionResult;
import com.jls.sandbox.service.JShellService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sandbox")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SandboxController {

    private final JShellService jShellService;

    @PostMapping("/execute")
    public ExecutionResult execute(@RequestBody ExecutionRequest request) {
        return jShellService.execute(request.getCode());
    }
}
