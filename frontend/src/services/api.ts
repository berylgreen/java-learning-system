import axios from 'axios';

const API_BASE_URL = 'http://localhost:8081/api/sandbox';

export interface ExecutionResult {
  output: string;
  error: string;
  value: string;
  success: boolean;
}

export const executeCode = async (code: string): Promise<ExecutionResult> => {
  try {
    const response = await axios.post<ExecutionResult>(`${API_BASE_URL}/execute`, {
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
