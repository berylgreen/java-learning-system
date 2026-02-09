import { useState } from 'react';
import ReactMarkdown from 'react-markdown';
import CodeEditor from './components/CodeEditor';
import { executeCode } from './services/api';
import type { ExecutionResult } from './services/api';
import './App.css';

const dummyMarkdown = `
# Java Basics: Hello World

In this lesson, you will learn how to write your first Java program.

## Task
Print "Hello, World!" to the console.

\`\`\`java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
\`\`\`
`;

const initialJavaCode = `System.out.println("Hello, World!");`;

function App() {
  const [code, setCode] = useState(initialJavaCode);
  const [result, setResult] = useState<ExecutionResult | null>(null);
  const [isRunning, setIsRunning] = useState(false);

  const handleRunCode = async () => {
    setIsRunning(true);
    setResult(null);
    try {
      const data = await executeCode(code);
      setResult(data);
    } catch (err) {
      setResult({
        output: '',
        error: 'An unexpected error occurred',
        value: '',
        success: false,
      });
    } finally {
      setIsRunning(false);
    }
  };

  return (
    <div className="app-container">
      <header className="app-header">
        <h1>Java Learning System</h1>
      </header>
      <main className="app-main">
        <div className="content-pane">
          <ReactMarkdown>{dummyMarkdown}</ReactMarkdown>
        </div>
        <div className="editor-pane">
          <CodeEditor
            initialCode={code}
            onChange={(val) => setCode(val || '')}
          />
          <div className="controls">
            <button
              className="run-button"
              onClick={handleRunCode}
              disabled={isRunning}
            >
              {isRunning ? 'Running...' : 'Run Code'}
            </button>
          </div>
          <div className="terminal">
            <div className="terminal-header">Terminal</div>
            <div className="terminal-body">
              {result && (
                <>
                  {result.output && <pre className="output">{result.output}</pre>}
                  {result.error && <pre className="error">{result.error}</pre>}
                  {!result.output && !result.error && result.success && (
                    <pre className="success">Execution successful (no output)</pre>
                  )}
                </>
              )}
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
