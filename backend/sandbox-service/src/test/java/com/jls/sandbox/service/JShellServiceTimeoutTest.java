package com.jls.sandbox.service;

import com.jls.sandbox.model.ExecutionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JShellServiceTimeoutTest {

    @Autowired
    private JShellService jShellService;

    @Test
    void testInfiniteLoopTimeout() {
        // Execute a code snippet that would run forever
        // The service should interrupt it and return a failure
        String code = "while(true) {}";

        long start = System.currentTimeMillis();
        ExecutionResult result = jShellService.execute(code);
        long duration = System.currentTimeMillis() - start;

        assertNotNull(result);
        assertFalse(result.isSuccess(), "Infinite loop should fail due to timeout");
        String error = result.getError();
        assertNotNull(error);
        assertTrue(error.contains("timed out"), "Error message should mention timeout. Found: [" + error + "]");

        // Assert it didn't take too long (e.g., less than 10 seconds if default is 5)
        assertTrue(duration < 10000, "Should have timed out faster than 10s. Took: " + duration + "ms");
    }
}
