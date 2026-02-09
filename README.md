# Java Learning System (JLS)

A modern, interactive Java learning platform featuring a web-based IDE, secure code execution sandbox, and structured course management system.

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green.svg)
![React](https://img.shields.io/badge/React-18-blue.svg)

## 🚀 Features

- **Interactive Web IDE**: Based on Monaco Editor (VS Code engine) with Java syntax highlighting.
- **Secure Code Sandbox**: Server-side Java execution using JShell API with security restrictions (timeout, forbidden packages).
- **Course Management**: Structured learning path (Courses -> Modules -> Lessons).
- **Smart Import Engine**: Convert Markdown/Text files into structured course content automatically.
- **Microservices Architecture**: Decoupled services for scalability.

## 🏗 Architecture

The system consists of three main components:

1.  **Frontend (`/frontend`)**:
    - Built with **React** + **TypeScript** + **Vite**.
    - Integrates **Monaco Editor** for code editing.
    - Communicates with backend services via REST API.

2.  **Sandbox Service (`/backend/sandbox-service`)**:
    - **Spring Boot 3** application running on port `8081`.
    - Uses **Java JShell API** to execute code snippets in-process.
    - Implements security controls (5s timeout, keyword blacklisting).

3.  **Course Service (`/backend/course-service`)**:
    - **Spring Boot 3** application running on port `8082`.
    - Manages course content using **H2 Database** (in-memory dev) / MySQL (prod).
    - Provides APIs for course retrieval and markdown import.

## 🛠 Tech Stack

- **Backend**: Java 21, Spring Boot 3, Spring Data JPA, H2/MySQL.
- **Frontend**: React, TypeScript, Vite, Monaco Editor, Axios.
- **Tools**: Maven, npm.

## 🏁 Getting Started

### Prerequisites

- JDK 17 or 21+
- Node.js 18+
- Maven 3.6+

### 1. Start the Sandbox Service

```bash
cd backend/sandbox-service
mvn spring-boot:run
```
*Runs on: http://localhost:8081*

### 2. Start the Course Service

```bash
cd backend/course-service
mvn spring-boot:run
```
*Runs on: http://localhost:8082*

### 3. Start the Frontend

```bash
cd frontend
npm install
npm run dev
```
*Runs on: http://localhost:5173*

## 🧪 Usage

1.  Open the frontend URL (http://localhost:5173).
2.  You will see the Code Editor on the right and Course Content on the left.
3.  Type Java code into the editor (e.g., `System.out.println("Hello JLS");`).
4.  Click **"Run Code"**.
5.  View the output in the terminal pane below.

### Importing Courses

To import a course, send a POST request to `http://localhost:8082/api/courses/import` with your Markdown content.

**Markdown Format:**
```markdown
# Course Title
## Module Title
### Lesson Title
Lesson content here...
\`\`\`java
System.out.println("Example Code");
\`\`\`
```

## 📂 Project Structure

```text
java-learning-system/
├── backend/
│   ├── sandbox-service/    # Code execution engine
│   └── course-service/     # Content management
├── frontend/               # React web application
├── docs/                   # Documentation & Plans
└── README.md
```

## 🛡 Security

The sandbox implements basic security measures:
- **Timeouts**: Execution stops after 5 seconds.
- **Static Analysis**: Blocks usage of `System.exit`, `Runtime.exec`, `java.io`, `java.net`, etc.

*Note: For production environments, consider wrapping the execution in OS-level isolation (e.g., nsjail or Docker).*

## 📄 License

This project is licensed under the MIT License.
