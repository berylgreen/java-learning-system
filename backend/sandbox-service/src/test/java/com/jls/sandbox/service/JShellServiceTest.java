package com.jls.sandbox.service;

import com.jls.sandbox.model.ExecutionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JShellServiceTest {

    @Autowired
    private JShellService jShellService;

    @Test
    void testExecuteSimpleMath() {
        ExecutionResult result = jShellService.execute("int a = 1; \n int b = 2; \n a + b");
        assertNotNull(result);
        assertTrue(result.isSuccess(), "Execution failed: " + result.getError());
        assertEquals("3", result.getValue());
    }

    @Test
    void testCaptureStdout() {
        ExecutionResult result = jShellService.execute("System.out.println(\"Hello World\");");
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals("Hello World" + System.lineSeparator(), result.getOutput());
    }

    @Test
    void testExecuteFullClassWithMain() {
        String code = "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello from Main\");\n" +
                "    }\n" +
                "}";
        ExecutionResult result = jShellService.execute(code);
        assertNotNull(result);
        assertTrue(result.isSuccess(), "Execution failed: " + result.getError());
        assertEquals("Hello from Main" + System.lineSeparator(), result.getOutput());
    }
}
