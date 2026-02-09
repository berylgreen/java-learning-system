# Java Learning System - Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Build a modern Java learning system featuring an interactive code sandbox, structured course content, and intelligent import tools.

**Architecture:**
- **Frontend:** React + TypeScript + Monaco Editor + Markdown Viewer.
- **Backend:** Spring Boot 3 (Course/User/Sandbox Services).
- **Sandbox:** JShell + Security Manager (No Docker), in-process execution.
- **Data:** H2 (Dev) / MySQL (Prod), Redis.

**Tech Stack:** Java 21, Spring Boot 3, React, TypeScript, Monaco Editor.

---

## Phase 1: Core Sandbox Service (The Heart)

### Task 1: Project Skeleton & Sandbox Service Setup

**Files:**
- Create: `backend/sandbox-service/pom.xml`
- Create: `backend/sandbox-service/src/main/java/com/jls/sandbox/SandboxApplication.java`
- Create: `backend/sandbox-service/src/main/resources/application.yml`

**Step 1: Create Maven Module Structure**

Create a multi-module Maven project or independent services. Let's start with a standalone `sandbox-service` for simplicity in Phase 1.

**pom.xml dependencies:**
- `spring-boot-starter-web`
- `spring-boot-starter-validation`
- `lombok`

**Step 2: Basic Spring Boot App**

Create `SandboxApplication.java` with `@SpringBootApplication`.

**Step 3: Health Check Controller**

Create `SandboxController.java` with a simple `/health` endpoint returning "OK".

**Step 4: Verify Build**

Run `mvn clean install` and start the app. Curl `/health`.

### Task 2: JShell Execution Engine

**Files:**
- Create: `backend/sandbox-service/src/main/java/com/jls/sandbox/service/JShellService.java`
- Create: `backend/sandbox-service/src/test/java/com/jls/sandbox/service/JShellServiceTest.java`

**Step 1: Write Failing Test (TDD)**

```java
@Test
void testExecuteSimpleMath() {
    JShellService service = new JShellService();
    String code = "int a = 1; int b = 2; a + b";
    ExecutionResult result = service.execute(code);
    assertEquals("3", result.getOutput().trim());
}
```

**Step 2: Implement JShell Wrapper**

- Use `jdk.jshell.JShell`.
- Create a `JShell` instance builder.
- Feed code via `sourceCodeAnalysis().analyzeCompletion(code)`.
- Use `eval(code)` to execute.
- Capture `SnippetEvent` to get value.

**Step 3: Capture Output**

- Configure `JShell.builder().out(printStream).err(printStream)`.
- Capture `System.out.println` content.

**Step 4: Verify Test Pass**

### Task 3: Security & Timeout (The Guard)

**Files:**
- Modify: `backend/sandbox-service/src/main/java/com/jls/sandbox/service/JShellService.java`
- Create: `backend/sandbox-service/src/main/java/com/jls/sandbox/security/SecurityPolicy.java` (Concept)

**Step 1: Timeout Implementation**

- Wrap `jshell.eval()` in a `CompletableFuture` or `ExecutorService`.
- Use `Future.get(timeout, TimeUnit.SECONDS)`.
- If timeout, `jshell.stop()` or close.

**Step 2: Restricted Imports**

- Clear default imports in JShell builder.
- Only allow `java.lang.*`, `java.util.*` (selectively).

**Step 3: Static Analysis (AST/String Check - Simple Version)**

- Before `eval()`, check code for forbidden keywords: `System.exit`, `Runtime.exec`, `java.io.File`, `Reflection`.
- *Note: A robust SecurityManager replacement is complex; for Phase 1, we use basic keyword filtering + JShell isolation.*

## Phase 2: Course & Import Service

### Task 4: Data Model & Course Service

**Files:**
- Create: `backend/course-service/pom.xml`
- Create: `backend/course-service/src/main/java/com/jls/course/model/Course.java`
- Create: `backend/course-service/src/main/java/com/jls/course/repository/CourseRepository.java`

**Step 1: Define Entities**

- `Course`: id, title, description.
- `Module`: id, course_id, title, order.
- `Lesson`: id, module_id, title, content (Markdown), code_snippet.

**Step 2: CRUD API**

- `GET /courses`
- `GET /courses/{id}`
- `GET /lessons/{id}`

### Task 5: Import Engine (The Converter)

**Files:**
- Create: `backend/course-service/src/main/java/com/jls/course/service/ImportService.java`

**Step 1: Text Parser**

- Implement a parser that takes a structured text file (e.g., Markdown with frontmatter or specific delimiters).
- Parse `Chapter 1: ...` into Modules.
- Parse `## Topic ...` into Lessons.

**Step 2: Upload Endpoint**

- `POST /api/import/course`: Upload ZIP/Text file.
- Trigger parsing and save to DB.

## Phase 3: Frontend Web Portal

### Task 6: React Setup & Monaco Editor

**Files:**
- Create: `frontend/package.json`
- Create: `frontend/src/components/CodeEditor.tsx`

**Step 1: Initialize Vite + React + TS**

`npm create vite@latest frontend -- --template react-ts`

**Step 2: Add Monaco Editor**

`npm install @monaco-editor/react`

**Step 3: Create Editor Component**

- Wrap Monaco Editor.
- Props: `initialCode`, `onChange`.

### Task 7: Sandbox Integration

**Files:**
- Create: `frontend/src/services/api.ts`
- Modify: `frontend/src/App.tsx`

**Step 1: Call Sandbox API**

- Implement `executeCode(code)` calling `POST /api/sandbox/execute`.

**Step 2: UI Integration**

- Split pane layout: Left (Markdown Content), Right (Code Editor + Output Console).
- "Run" button triggers API and displays stdout in Console.

---
