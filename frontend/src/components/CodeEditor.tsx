import Editor, { loader } from '@monaco-editor/react';

loader.config({
  paths: {
    vs: 'https://registry.npmmirror.com/monaco-editor/0.44.0/files/min/vs',
  },
});

interface CodeEditorProps {
  code?: string;
  onChange?: (value: string | undefined) => void;
}

const CodeEditor = ({ code = '', onChange }: CodeEditorProps) => {
  return (
    <div style={{ height: '60vh', border: '1px solid #374151' }}>
      <Editor
        height="100%"
        defaultLanguage="java"
        value={code}
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
