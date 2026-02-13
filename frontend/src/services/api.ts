import axios from 'axios';

const SANDBOX_API_BASE_URL = 'http://localhost:8081/api/sandbox';
const COURSE_API_BASE_URL = 'http://localhost:8082/api/courses';

export interface ExecutionResult {
  output: string;
  error: string;
  value: string;
  success: boolean;
}

export interface CourseSummary {
  id: number;
  title: string;
  description?: string;
}

export interface LessonSummary {
  id: number;
  title: string;
}

export interface ModuleStructure {
  id: number;
  title: string;
  orderIndex: number;
  lessons: LessonSummary[];
}

export interface CourseStructure {
  id: number;
  title: string;
  description?: string;
  modules: ModuleStructure[];
}

export interface LessonDetail {
  id: number;
  title: string;
  content?: string;
  codeSnippet?: string;
}

export interface ExerciseTestCase {
  input: string;
  expectedOutput: string;
  timeoutMs: number;
}

export interface Exercise {
  id: number;
  lessonId: number;
  title: string;
  description?: string;
  starterCode?: string;
  publicTestCases: ExerciseTestCase[];
}

export interface ExerciseCaseResult {
  input: string;
  expectedOutput: string;
  actualOutput: string;
  passed: boolean;
  error?: string;
}

export interface ExerciseSubmissionResult {
  submissionId: number;
  status: 'PASS' | 'FAIL' | 'ERROR';
  score: number;
  message: string;
  publicResults: ExerciseCaseResult[];
  hiddenSummary: {
    passed: number;
    total: number;
  };
  durationMs: number;
  createdAt: string;
}

export const executeCode = async (code: string): Promise<ExecutionResult> => {
  try {
    const response = await axios.post<ExecutionResult>(`${SANDBOX_API_BASE_URL}/execute`, {
      code,
    });
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error) && error.response) {
      return {
        output: '',
        error: error.response.data.message || 'Server error occurred',
        value: '',
        success: false,
      };
    }
    return {
      output: '',
      error: 'Failed to connect to the sandbox service',
      value: '',
      success: false,
    };
  }
};

export const getCourses = async (): Promise<CourseSummary[]> => {
  const response = await axios.get<CourseSummary[]>(COURSE_API_BASE_URL);
  return response.data;
};

export const getCourseStructure = async (courseId: number): Promise<CourseStructure> => {
  const response = await axios.get<CourseStructure>(`${COURSE_API_BASE_URL}/${courseId}/structure`);
  return response.data;
};

export const getLessonDetail = async (lessonId: number): Promise<LessonDetail> => {
  const response = await axios.get<LessonDetail>(`${COURSE_API_BASE_URL}/lessons/${lessonId}`);
  return response.data;
};

export const importCourseFromMarkdown = async (markdown: string): Promise<CourseSummary> => {
  const response = await axios.post<CourseSummary>(`${COURSE_API_BASE_URL}/import`, markdown, {
    headers: {
      'Content-Type': 'text/plain; charset=utf-8',
    },
  });
  return response.data;
};

export const importCourseFromSourceIndex = async (
  sourceIndexJson: string,
): Promise<CourseSummary> => {
  const response = await axios.post<CourseSummary>(
    `${COURSE_API_BASE_URL}/import/source-index`,
    sourceIndexJson,
    {
      headers: {
        'Content-Type': 'application/json',
      },
    },
  );
  return response.data;
};

export const getLessonExercise = async (lessonId: number): Promise<Exercise> => {
  const response = await axios.get<Exercise>(`${COURSE_API_BASE_URL}/lessons/${lessonId}/exercise`);
  return response.data;
};

export const submitLessonExercise = async (
  lessonId: number,
  code: string,
): Promise<ExerciseSubmissionResult> => {
  const response = await axios.post<ExerciseSubmissionResult>(
    `${COURSE_API_BASE_URL}/lessons/${lessonId}/exercise/submissions`,
    { code },
  );
  return response.data;
};

export const getLatestLessonExerciseSubmission = async (
  lessonId: number,
): Promise<ExerciseSubmissionResult> => {
  const response = await axios.get<ExerciseSubmissionResult>(
    `${COURSE_API_BASE_URL}/lessons/${lessonId}/exercise/submissions/latest`,
  );
  return response.data;
};

export const deleteCourse = async (courseId: number): Promise<void> => {
  await axios.delete(`${COURSE_API_BASE_URL}/${courseId}`);
};
