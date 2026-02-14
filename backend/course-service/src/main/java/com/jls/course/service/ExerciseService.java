package com.jls.course.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jls.course.dto.ExerciseCaseResultDTO;
import com.jls.course.dto.ExerciseDTO;
import com.jls.course.dto.ExerciseHiddenSummaryDTO;
import com.jls.course.dto.ExerciseSubmissionRequestDTO;
import com.jls.course.dto.ExerciseSubmissionResultDTO;
import com.jls.course.dto.ExerciseTestCaseDTO;
import com.jls.course.dto.SandboxExecutionRequestDTO;
import com.jls.course.dto.SandboxExecutionResultDTO;
import com.jls.course.model.Exercise;
import com.jls.course.model.ExerciseSubmission;
import com.jls.course.model.Lesson;
import com.jls.course.repository.ExerciseRepository;
import com.jls.course.repository.ExerciseSubmissionRepository;
import com.jls.course.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private static final TypeReference<List<ExerciseTestCaseDTO>> TEST_CASE_LIST_TYPE = new TypeReference<>() {};
    private static final TypeReference<List<ExerciseCaseResultDTO>> CASE_RESULT_LIST_TYPE = new TypeReference<>() {};

    private final ExerciseRepository exerciseRepository;
    private final ExerciseSubmissionRepository exerciseSubmissionRepository;
    private final LessonRepository lessonRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Value("${sandbox.base-url:http://localhost:8081}")
    private String sandboxBaseUrl;

    @Transactional(readOnly = true)
    public ExerciseDTO getExerciseByLessonId(Long lessonId) {
        Exercise exercise = exerciseRepository.findByLessonId(lessonId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise not found for lesson id: " + lessonId
                ));

        return ExerciseDTO.builder()
                .id(exercise.getId())
                .lessonId(exercise.getLesson().getId())
                .title(exercise.getTitle())
                .description(exercise.getDescription())
                .starterCode(exercise.getStarterCode())
                .answerHint(exercise.getAnswerHint())
                .answerCode(exercise.getAnswerCode())
                .publicTestCases(parseTestCases(exercise.getPublicTestCasesJson()))
                .build();
    }

    @Transactional
    public ExerciseSubmissionResultDTO submitAndJudge(Long lessonId, ExerciseSubmissionRequestDTO request) {
        if (request == null || request.getCode() == null || request.getCode().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Submission code cannot be empty");
        }

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Lesson not found with id: " + lessonId
                ));
        Exercise exercise = exerciseRepository.findByLesson(lesson)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise not found for lesson id: " + lessonId
                ));

        List<ExerciseTestCaseDTO> publicCases = parseTestCases(exercise.getPublicTestCasesJson());
        List<ExerciseTestCaseDTO> hiddenCases = parseTestCases(exercise.getHiddenTestCasesJson());

        long start = System.currentTimeMillis();
        List<ExerciseCaseResultDTO> publicResults = runCases(request.getCode(), publicCases);
        List<ExerciseCaseResultDTO> hiddenResults = runCases(request.getCode(), hiddenCases);
        long durationMs = System.currentTimeMillis() - start;

        int publicPassed = countPassed(publicResults);
        int hiddenPassed = countPassed(hiddenResults);
        int publicTotal = publicResults.size();
        int hiddenTotal = hiddenResults.size();
        boolean pass = publicPassed == publicTotal && hiddenPassed == hiddenTotal;

        int total = publicTotal + hiddenTotal;
        int passedTotal = publicPassed + hiddenPassed;
        int score = total == 0 ? 0 : (int) Math.round((passedTotal * 100.0) / total);

        String message = pass ? "判题通过" : "判题未通过，请根据用例结果调整代码";

        ExerciseSubmission submission = ExerciseSubmission.builder()
                .exercise(exercise)
                .code(request.getCode())
                .status(pass ? "PASS" : "FAIL")
                .publicPassed(publicPassed)
                .publicTotal(publicTotal)
                .hiddenPassed(hiddenPassed)
                .hiddenTotal(hiddenTotal)
                .score(score)
                .publicCaseResultsJson(writeAsJson(publicResults))
                .errorMessage(null)
                .durationMs(durationMs)
                .createdAt(LocalDateTime.now())
                .build();
        submission = exerciseSubmissionRepository.save(submission);

        return ExerciseSubmissionResultDTO.builder()
                .submissionId(submission.getId())
                .status(submission.getStatus())
                .score(score)
                .message(message)
                .publicResults(publicResults)
                .hiddenSummary(ExerciseHiddenSummaryDTO.builder().passed(hiddenPassed).total(hiddenTotal).build())
                .durationMs(durationMs)
                .createdAt(submission.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public ExerciseSubmissionResultDTO getLatestSubmission(Long lessonId) {
        Exercise exercise = exerciseRepository.findByLessonId(lessonId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise not found for lesson id: " + lessonId
                ));

        ExerciseSubmission submission = exerciseSubmissionRepository.findTopByExerciseOrderByCreatedAtDesc(exercise)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No submission found for lesson id: " + lessonId
                ));

        List<ExerciseCaseResultDTO> publicResults = parseCaseResults(submission.getPublicCaseResultsJson());

        return ExerciseSubmissionResultDTO.builder()
                .submissionId(submission.getId())
                .status(submission.getStatus())
                .score(submission.getScore())
                .message(submission.getStatus().equals("PASS") ? "最近一次判题通过" : "最近一次判题未通过")
                .publicResults(publicResults)
                .hiddenSummary(ExerciseHiddenSummaryDTO.builder()
                        .passed(submission.getHiddenPassed())
                        .total(submission.getHiddenTotal())
                        .build())
                .durationMs(submission.getDurationMs())
                .createdAt(submission.getCreatedAt())
                .build();
    }

    private List<ExerciseCaseResultDTO> runCases(String code, List<ExerciseTestCaseDTO> testCases) {
        List<ExerciseCaseResultDTO> results = new ArrayList<>();
        for (ExerciseTestCaseDTO testCase : testCases) {
            String mergedCode = buildExecutableCode(code, testCase.getInput());
            SandboxExecutionResultDTO execution = callSandbox(mergedCode);

            String actualOutput = normalize(execution.getOutput());
            String expectedOutput = normalize(testCase.getExpectedOutput());
            boolean passed = execution.isSuccess() && actualOutput.equals(expectedOutput);

            results.add(ExerciseCaseResultDTO.builder()
                    .input(testCase.getInput())
                    .expectedOutput(testCase.getExpectedOutput())
                    .actualOutput(execution.getOutput())
                    .passed(passed)
                    .error(execution.getError())
                    .build());
        }
        return results;
    }

    private SandboxExecutionResultDTO callSandbox(String code) {
        ResponseEntity<SandboxExecutionResultDTO> response = restTemplate.postForEntity(
                sandboxBaseUrl + "/api/sandbox/execute",
                SandboxExecutionRequestDTO.builder().code(code).build(),
                SandboxExecutionResultDTO.class
        );
        SandboxExecutionResultDTO body = response.getBody();
        if (body == null) {
            throw new RuntimeException("Sandbox execute response is empty");
        }
        return body;
    }

    private String buildExecutableCode(String userCode, String input) {
        String safeInput = input == null ? "" : input;
        return userCode + "\n" + "System.out.print(solution(\"" + escapeForJava(safeInput) + "\"));";
    }

    private String escapeForJava(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    private String normalize(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\r\n", "\n").trim();
    }

    private int countPassed(List<ExerciseCaseResultDTO> results) {
        return (int) results.stream().filter(ExerciseCaseResultDTO::isPassed).count();
    }

    private List<ExerciseTestCaseDTO> parseTestCases(String json) {
        if (json == null || json.isBlank()) {
            return List.of();
        }
        try {
            return objectMapper.readValue(json, TEST_CASE_LIST_TYPE);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Exercise test cases json parse failed", e);
        }
    }

    private List<ExerciseCaseResultDTO> parseCaseResults(String json) {
        if (json == null || json.isBlank()) {
            return List.of();
        }
        try {
            return objectMapper.readValue(json, CASE_RESULT_LIST_TYPE);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Exercise case results json parse failed", e);
        }
    }

    private String writeAsJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Write json failed", e);
        }
    }
}
