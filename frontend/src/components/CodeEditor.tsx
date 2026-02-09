import React from 'react';
import Editor, { loader } from '@monaco-editor/react';

// Configure to use a faster CDN (npmmirror)
loader.config({
  paths: {
    vs: 'https://registry.npmmirror.com/monaco-editor/0.44.0/files/min/vs',
  },
});

interface CodeEditorProps {
  initialCode?: string;
  onChange?: (value: string | undefined) => void;
}

const CodeEditor: React.FC<CodeEditorProps> = ({ initialCode = '', onChange }) => {
  return (
    <div style={{ height: '60vh', border: '1px solid #ccc' }}>
      <Editor
        height="100%"
        defaultLanguage="java"
        defaultValue={initialCode}
        theme="vs-dark"
        onChange={onChange}
        options={{
          minimap: { enabled: false },
          fontSize: 14,
          automaticLayout: true,
        }}
      />
    </div>
  );
};

export default CodeEditor;
