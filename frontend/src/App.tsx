import { useEffect, useMemo, useRef, useState } from 'react';
import type { ChangeEvent } from 'react';
import ReactMarkdown from 'react-markdown';
import CodeEditor from './components/CodeEditor';
import {
  executeCode,
  getCourseStructure,
  getCourses,
  getLessonDetail,
  importCourseFromMarkdown,
  importCourseFromSourceIndex,
} from './services/api';
import type {
  CourseStructure,
  CourseSummary,
  ExecutionResult,
  LessonDetail,
  LessonSummary,
  ModuleStructure,
} from './services/api';
import './App.css';

function App() {
  const [courses, setCourses] = useState<CourseSummary[]>([]);
  const [selectedCourseId, setSelectedCourseId] = useState<number | null>(null);
  const [courseStructure, setCourseStructure] = useState<CourseStructure | null>(null);
  const [selectedLessonId, setSelectedLessonId] = useState<number | null>(null);
  const [lessonDetail, setLessonDetail] = useState<LessonDetail | null>(null);

  const [code, setCode] = useState('System.out.println("Hello, World!");');
  const [result, setResult] = useState<ExecutionResult | null>(null);
  const [isRunning, setIsRunning] = useState(false);

  const [isLoadingCourses, setIsLoadingCourses] = useState(true);
  const [isLoadingStructure, setIsLoadingStructure] = useState(false);
  const [isLoadingLesson, setIsLoadingLesson] = useState(false);
  const [loadError, setLoadError] = useState<string | null>(null);

  const [sourceIndexJson, setSourceIndexJson] = useState('');
  const [markdownContent, setMarkdownContent] = useState('');
  const [isImportingSourceIndex, setIsImportingSourceIndex] = useState(false);
  const [isImportingMarkdown, setIsImportingMarkdown] = useState(false);
  const [importError, setImportError] = useState<string | null>(null);
  const [importSuccess, setImportSuccess] = useState<string | null>(null);
  const [isImportDialogOpen, setIsImportDialogOpen] = useState(false);

  const lessonRefs = useRef<Record<number, HTMLButtonElement | null>>({});

  const loadCourses = async (
    options: { selectCourseId?: number; fallbackToFirst?: boolean } = { fallbackToFirst: true },
  ): Promise<boolean> => {
    setIsLoadingCourses(true);
    setLoadError(null);
    try {
      const data = await getCourses();
      setCourses(data);

      if (data.length === 0) {
        setSelectedCourseId(null);
        setCourseStructure(null);
        setSelectedLessonId(null);
        setLessonDetail(null);
        return true;
      }

      if (options.selectCourseId && data.some((course) => course.id === options.selectCourseId)) {
        setSelectedCourseId(options.selectCourseId);
        return true;
      }

      if (options.fallbackToFirst) {
        setSelectedCourseId((currentId) =>
          currentId && data.some((course) => course.id === currentId) ? currentId : data[0].id,
        );
      }

      return true;
    } catch {
      setLoadError('课程列表加载失败，请确认 Course Service 已启动 (8082)。');
      return false;
    } finally {
      setIsLoadingCourses(false);
    }
  };

  const handleSourceIndexFileChange = async (event: ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    event.target.value = '';
    if (!file) {
      return;
    }

    const maxFileSize = 512 * 1024;
    if (file.size > maxFileSize) {
      setImportSuccess(null);
      setImportError('JSON 文件过大，请控制在 512KB 以内。');
      return;
    }

    try {
      const text = await file.text();
      setSourceIndexJson(text);
      setImportError(null);
      setImportSuccess(`已加载文件：${file.name}`);
    } catch {
      setImportSuccess(null);
      setImportError('读取文件失败，请重试。');
    }
  };

  const handleImportSourceIndex = async () => {
    if (!sourceIndexJson.trim()) {
      setImportSuccess(null);
      setImportError('请先粘贴或上传源码索引 JSON。');
      return;
    }

    setIsImportingSourceIndex(true);
    setImportError(null);
    setImportSuccess(null);

    try {
      const importedCourse = await importCourseFromSourceIndex(sourceIndexJson);
      const loadSuccess = await loadCourses({ selectCourseId: importedCourse.id, fallbackToFirst: true });
      if (loadSuccess) {
        setImportSuccess(`源码索引导入成功：${importedCourse.title}`);
      }
    } catch {
      setImportError('源码索引导入失败，请检查 JSON 格式或后端日志。');
    } finally {
      setIsImportingSourceIndex(false);
    }
  };

  const handleMarkdownFileChange = async (event: ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    event.target.value = '';
    if (!file) {
      return;
    }

    const maxFileSize = 1024 * 1024;
    if (file.size > maxFileSize) {
      setImportSuccess(null);
      setImportError('Markdown 文件过大，请控制在 1MB 以内。');
      return;
    }

    try {
      const text = await file.text();
      setMarkdownContent(text);
      setImportError(null);
      setImportSuccess(`已加载文件：${file.name}`);
    } catch {
      setImportSuccess(null);
      setImportError('读取 Markdown 文件失败，请重试。');
    }
  };

  const handleImportMarkdown = async () => {
    if (!markdownContent.trim()) {
      setImportSuccess(null);
      setImportError('请先粘贴或上传 Markdown 内容。');
      return;
    }

    setIsImportingMarkdown(true);
    setImportError(null);
    setImportSuccess(null);

    try {
      const importedCourse = await importCourseFromMarkdown(markdownContent);
      const loadSuccess = await loadCourses({ selectCourseId: importedCourse.id, fallbackToFirst: true });
      if (loadSuccess) {
        setImportSuccess(`Markdown 导入成功：${importedCourse.title}`);
      }
    } catch {
      setImportError('Markdown 导入失败，请检查内容格式或后端日志。');
    } finally {
      setIsImportingMarkdown(false);
    }
  };

  useEffect(() => {
    void loadCourses({ fallbackToFirst: true });
  }, []);

  useEffect(() => {
    if (!selectedCourseId) {
      setCourseStructure(null);
      return;
    }

    const loadStructure = async () => {
      setIsLoadingStructure(true);
      setLoadError(null);
      try {
        const data = await getCourseStructure(selectedCourseId);
        setCourseStructure(data);

        const firstLessonId = data.modules?.[0]?.lessons?.[0]?.id ?? null;
        setSelectedLessonId(firstLessonId);
      } catch {
        setLoadError('课程结构加载失败，请检查后端接口 /api/courses/{id}/structure。');
      } finally {
        setIsLoadingStructure(false);
      }
    };

    void loadStructure();
  }, [selectedCourseId]);

  useEffect(() => {
    if (!selectedLessonId) {
      setLessonDetail(null);
      return;
    }

    const loadLesson = async () => {
      setIsLoadingLesson(true);
      setLoadError(null);
      try {
        const data = await getLessonDetail(selectedLessonId);
        setLessonDetail(data);
        if (data.codeSnippet && data.codeSnippet.trim().length > 0) {
          setCode(data.codeSnippet);
        }
      } catch {
        setLoadError('课时详情加载失败，请检查后端接口 /api/courses/lessons/{lessonId}。');
      } finally {
        setIsLoadingLesson(false);
      }
    };

    void loadLesson();
  }, [selectedLessonId]);

  useEffect(() => {
    if (!selectedLessonId) {
      return;
    }

    const target = lessonRefs.current[selectedLessonId];
    if (target) {
      target.scrollIntoView({ block: 'nearest', behavior: 'smooth' });
    }
  }, [selectedLessonId, courseStructure]);

  const handleRunCode = async () => {
    setIsRunning(true);
    setResult(null);
    try {
      const data = await executeCode(code);
      setResult(data);
    } catch {
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

  const sidebarModules: ModuleStructure[] = useMemo(
    () => courseStructure?.modules ?? [],
    [courseStructure],
  );

  const flatLessons = useMemo(() => {
    const lessons: LessonSummary[] = [];
    sidebarModules.forEach((module) => {
      module.lessons.forEach((lesson) => lessons.push(lesson));
    });
    return lessons;
  }, [sidebarModules]);

  const currentLessonIndex = useMemo(() => {
    if (!selectedLessonId) {
      return -1;
    }
    return flatLessons.findIndex((lesson) => lesson.id === selectedLessonId);
  }, [flatLessons, selectedLessonId]);

  const previousLesson = currentLessonIndex > 0 ? flatLessons[currentLessonIndex - 1] : null;
  const nextLesson =
    currentLessonIndex >= 0 && currentLessonIndex < flatLessons.length - 1
      ? flatLessons[currentLessonIndex + 1]
      : null;

  const renderSidebar = () => {
    return (
      <>
        <div className="course-switcher">
          <div className="course-switcher-header">
            <label htmlFor="course-select">课程</label>
            <button
              type="button"
              className="open-import-dialog-btn"
              onClick={() => {
                setIsImportDialogOpen(true);
                setImportError(null);
                setImportSuccess(null);
              }}
            >
              导入
            </button>
          </div>

          {isLoadingCourses ? (
            <div className="sidebar-state">加载课程中...</div>
          ) : courses.length === 0 ? (
            <div className="sidebar-state">暂无课程，请先导入课程内容。</div>
          ) : (
            <select
              id="course-select"
              value={selectedCourseId ?? ''}
              onChange={(e) => setSelectedCourseId(Number(e.target.value))}
            >
              {courses.map((course) => (
                <option key={course.id} value={course.id}>
                  {course.title}
                </option>
              ))}
            </select>
          )}
        </div>

        {!isLoadingCourses && courses.length > 0 &&
          (isLoadingStructure ? (
            <div className="sidebar-state">加载目录中...</div>
          ) : (
            <div className="module-list">
              {sidebarModules.map((module) => (
                <div key={module.id} className="module-block">
                  <div className="module-title">{module.title}</div>
                  <ul className="lesson-list">
                    {module.lessons.map((lesson: LessonSummary) => (
                      <li key={lesson.id}>
                        <button
                          type="button"
                          ref={(el) => {
                            lessonRefs.current[lesson.id] = el;
                          }}
                          className={`lesson-item ${selectedLessonId === lesson.id ? 'active' : ''}`}
                          onClick={() => setSelectedLessonId(lesson.id)}
                        >
                          {lesson.title}
                        </button>
                      </li>
                    ))}
                  </ul>
                </div>
              ))}
            </div>
          ))}
      </>
    );
  };

  return (
    <div className="app-container">
      <header className="app-header">
        <h1>Java Learning System</h1>
      </header>
      <main className="app-main">
        <aside className="sidebar">{renderSidebar()}</aside>

        {isImportDialogOpen && (
          <div className="import-dialog-mask" onClick={() => setIsImportDialogOpen(false)}>
            <div className="import-dialog" onClick={(e) => e.stopPropagation()}>
              <div className="import-dialog-header">
                <h3>导入课程内容</h3>
                <button
                  type="button"
                  className="close-dialog-btn"
                  onClick={() => setIsImportDialogOpen(false)}
                >
                  ×
                </button>
              </div>

              <div className="source-index-import">
                <div className="source-index-title">Markdown 导入</div>
                <textarea
                  value={markdownContent}
                  onChange={(e) => {
                    setMarkdownContent(e.target.value);
                    setImportError(null);
                    setImportSuccess(null);
                  }}
                  placeholder="粘贴 course-java-basics.md 内容"
                  rows={6}
                />
                <div className="source-index-actions">
                  <label className="upload-file-btn" htmlFor="markdown-file-dialog">
                    上传 .md
                  </label>
                  <input
                    id="markdown-file-dialog"
                    type="file"
                    accept="text/markdown,.md"
                    onChange={handleMarkdownFileChange}
                  />
                  <button
                    type="button"
                    className="import-source-btn"
                    onClick={handleImportMarkdown}
                    disabled={isImportingMarkdown || !markdownContent.trim()}
                  >
                    {isImportingMarkdown ? '导入中...' : '导入 Markdown'}
                  </button>
                </div>
              </div>

              <div className="source-index-import">
                <div className="source-index-title">源码索引导入</div>
                <textarea
                  value={sourceIndexJson}
                  onChange={(e) => {
                    setSourceIndexJson(e.target.value);
                    setImportError(null);
                    setImportSuccess(null);
                  }}
                  placeholder="粘贴 course-java-basics-source-index.json 内容"
                  rows={6}
                />
                <div className="source-index-actions">
                  <label className="upload-file-btn" htmlFor="source-index-file-dialog">
                    上传 JSON
                  </label>
                  <input
                    id="source-index-file-dialog"
                    type="file"
                    accept="application/json,.json"
                    onChange={handleSourceIndexFileChange}
                  />
                  <button
                    type="button"
                    className="import-source-btn"
                    onClick={handleImportSourceIndex}
                    disabled={isImportingSourceIndex || !sourceIndexJson.trim()}
                  >
                    {isImportingSourceIndex ? '导入中...' : '导入源码索引'}
                  </button>
                </div>
              </div>

              {importError && <div className="import-error">{importError}</div>}
              {importSuccess && <div className="import-success">{importSuccess}</div>}
            </div>
          </div>
        )}

        <section className="content-pane">
          {loadError && <div className="content-error">{loadError}</div>}
          {isLoadingLesson ? (
            <div className="content-loading">加载课时内容中...</div>
          ) : lessonDetail ? (
            <>
              <div className="lesson-header-row">
                <h2>{lessonDetail.title}</h2>
                <div className="lesson-nav-buttons">
                  <button
                    type="button"
                    className="lesson-nav-btn"
                    onClick={() => previousLesson && setSelectedLessonId(previousLesson.id)}
                    disabled={!previousLesson}
                  >
                    上一课
                  </button>
                  <button
                    type="button"
                    className="lesson-nav-btn"
                    onClick={() => nextLesson && setSelectedLessonId(nextLesson.id)}
                    disabled={!nextLesson}
                  >
                    下一课
                  </button>
                </div>
              </div>
              <ReactMarkdown>{lessonDetail.content || '暂无内容'}</ReactMarkdown>
            </>
          ) : (
            <div className="content-loading">请选择左侧课时开始学习</div>
          )}
        </section>

        <section className="editor-pane">
          <CodeEditor code={code} onChange={(val) => setCode(val || '')} />
          <div className="controls">
            <button className="run-button" onClick={handleRunCode} disabled={isRunning}>
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
        </section>
      </main>
    </div>
  );
}

export default App;
