package com.jls.course.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jls.course.dto.ExerciseSubmissionRequestDTO;
import com.jls.course.dto.ExerciseSubmissionResultDTO;
import com.jls.course.dto.ExerciseTestCaseDTO;
import com.jls.course.dto.SandboxExecutionResultDTO;
import com.jls.course.model.Course;
import com.jls.course.model.Exercise;
import com.jls.course.model.Lesson;
import com.jls.course.model.Module;
import com.jls.course.repository.CourseRepository;
import com.jls.course.repository.ExerciseRepository;
import com.jls.course.repository.LessonRepository;
import com.jls.course.repository.ModuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@TestPropertySource(properties = "sandbox.base-url=http://localhost:8081")
@Transactional
class ExerciseServiceTest {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private Lesson lesson;

    @BeforeEach
    void setUp() throws Exception {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        Course course = courseRepository.save(Course.builder().title("Test Course").description("desc").build());
        Module module = moduleRepository.save(Module.builder().title("Module 1").orderIndex(1).course(course).build());
        lesson = lessonRepository.save(Lesson.builder()
                .title("Lesson 1")
                .content("content")
                .codeSnippet("class Main {}")
                .module(module)
                .build());

        List<ExerciseTestCaseDTO> publicCases = List.of(
                ExerciseTestCaseDTO.builder().input("abc").expectedOutput("ABC").timeoutMs(2000L).build()
        );
        List<ExerciseTestCaseDTO> hiddenCases = List.of(
                ExerciseTestCaseDTO.builder().input("xyz").expectedOutput("XYZ").timeoutMs(2000L).build()
        );

        exerciseRepository.save(Exercise.builder()
                .lesson(lesson)
                .title("Uppercase")
                .description("实现大写转换")
                .starterCode("String solution(String input){return input.toUpperCase();}")
                .publicTestCasesJson(objectMapper.writeValueAsString(publicCases))
                .hiddenTestCasesJson(objectMapper.writeValueAsString(hiddenCases))
                .passRule("ALL_PASS")
                .build());
    }

    @Test
    void submitAndJudge_shouldPassWhenAllCasesPass() throws Exception {
        String sandboxOk = objectMapper.writeValueAsString(
                SandboxExecutionResultDTO.builder().output("ABC").error("").value("").success(true).build()
        );
        String sandboxOk2 = objectMapper.writeValueAsString(
                SandboxExecutionResultDTO.builder().output("XYZ").error("").value("").success(true).build()
        );

        mockServer.expect(requestTo("http://localhost:8081/api/sandbox/execute"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(sandboxOk, MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo("http://localhost:8081/api/sandbox/execute"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(sandboxOk2, MediaType.APPLICATION_JSON));

        ExerciseSubmissionResultDTO result = exerciseService.submitAndJudge(
                lesson.getId(),
                ExerciseSubmissionRequestDTO.builder().code("String solution(String input){return input.toUpperCase();}").build()
        );

        assertNotNull(result.getSubmissionId());
        assertEquals("PASS", result.getStatus());
        assertEquals(100, result.getScore());
        assertEquals(1, result.getHiddenSummary().getPassed());
        assertEquals(1, result.getPublicResults().size());

        mockServer.verify();
    }

    @Test
    void getExerciseByLessonId_shouldThrowWhenExerciseMissing() {
        Lesson missingLesson = lessonRepository.save(Lesson.builder()
                .title("Lesson Missing")
                .content("none")
                .codeSnippet("")
                .module(lesson.getModule())
                .build());

        assertThrows(ResponseStatusException.class, () -> exerciseService.getExerciseByLessonId(missingLesson.getId()));
    }
}
