# 练习 + 自动判题全链路设计（首版）

- 日期：2026-02-14
- 状态：已评审（脑暴确认）
- 目标范围：首版落地「每课 1 题 + 混合判题（公开用例 + 隐藏用例）」

## 1. 背景与目标

当前系统已具备课程浏览、课时详情、代码编辑与运行、Markdown/源码索引导入能力。下一步目标是把“学”和“练”闭环打通：用户在课时内即可做题并获得即时反馈。

首版目标：
1. 每个 Lesson 支持 1 道练习题。
2. 支持自动判题，采用混合模式：公开用例细节反馈 + 隐藏用例汇总判定。
3. 保持现有微服务结构，不新增服务，快速上线验证价值。
4. 全链路可追踪：提交记录可落库并可回看最近一次结果。

## 2. 范围与非目标（YAGNI）

### 2.1 首版范围
- Lesson 维度的练习展示与提交。
- 判题流程：`course-service` 编排，`sandbox-service` 执行。
- 结果展示：总状态、公开用例逐条结果、隐藏用例汇总。
- 最近一次提交结果查询。

### 2.2 非目标
- 不做题库中心页。
- 不做每课多题/难度分层。
- 不做用户体系强绑定（如排行榜、班级统计）。
- 不引入新微服务（如 exercise-service）。

## 3. 方案选择结论

对比三种候选方案后，选择 **方案 A：在现有服务内扩展练习与判题能力**。

结论依据：
- 复用现有架构，改造成本低、联调路径短。
- 能最快验证“练习+反馈”对学习体验的提升。
- 后续若题量和复杂度上升，可平滑拆分为独立 `exercise-service`。

## 4. 架构与职责

### 4.1 服务职责
- `course-service`
  - 维护 Exercise 配置与 Submission 记录。
  - 接收前端提交并执行判题编排。
  - 聚合判题结果并返回前端。
- `sandbox-service`
  - 仅负责执行代码片段。
  - 返回 stdout/stderr/执行状态（沿用现有能力）。

### 4.2 前端职责
- 在课时页增加“练习面板”。
- 支持加载题目、提交判题、展示最近结果。
- 保留当前代码编辑区，提交失败不清空代码。

## 5. 数据模型设计

## 5.1 Exercise（与 Lesson 1:1）
- `id`
- `lessonId`（唯一）
- `title`
- `description`
- `starterCode`
- `publicTestCasesJson`
- `hiddenTestCasesJson`
- `passRule`（首版固定 all-pass）
- `updatedAt`

测试用例结构建议：
```json
{
  "input": "...",
  "expectedOutput": "...",
  "timeoutMs": 2000
}
```

### 5.2 Submission（每次提交一条）
- `id`
- `exerciseId`
- `lessonId`
- `code`
- `status`（PASS / FAIL / ERROR）
- `publicPassed`
- `publicTotal`
- `hiddenPassed`
- `hiddenTotal`
- `score`
- `publicCaseResultsJson`
- `errorMessage`
- `durationMs`
- `createdAt`

## 6. API 设计（course-service）

### 6.1 获取课时练习
`GET /api/courses/lessons/{lessonId}/exercise`

返回：题目元信息 + 模板代码 + 公开用例说明（不返回隐藏用例内容）。

### 6.2 提交并判题
`POST /api/courses/lessons/{lessonId}/exercise/submissions`

请求体：
```json
{
  "code": "..."
}
```

返回：
- `status`
- `score`
- `publicResults[]`（逐条）
- `hiddenSummary`（通过数/总数）
- `message`

### 6.3 最近一次提交
`GET /api/courses/lessons/{lessonId}/exercise/submissions/latest`

返回最近一次 Submission 的展示字段。

## 7. 判题流程与规则

1. 校验 lesson 是否存在练习。
2. 拉取 Exercise 配置。
3. 先跑公开用例，再跑隐藏用例（首版串行，稳定优先）。
4. 每组用例调用 `sandbox-service /execute`。
5. 归一化输出后比较（统一换行、去尾部空白）。
6. 生成公开明细 + 隐藏汇总。
7. 按 `all-pass` 规则判定 PASS/FAIL。
8. 落库 Submission，并将结果返回前端。

> 隐藏用例仅返回汇总，不回传输入和期望，避免“反向刷题”。

## 8. 前端交互设计

### 8.1 布局
- 在课时页内容区加入“练习面板”（不新开页面）。
- 面板包含：题目标题、描述、模板代码按钮、运行并判题按钮、最近结果区。

### 8.2 交互
- 进入课时：并行拉取 lesson 与 exercise。
- 点击“运行并判题”：按钮 loading 且防重复点击。
- 成功后展示：总状态、公开用例逐条结果、隐藏用例汇总。
- 失败后保留当前代码，支持快速重试。

## 9. 错误处理策略

统一按三类错误对前端返回可渲染信息：
1. 网络/服务错误：如判题服务不可用。
2. 代码错误：编译失败、运行异常、超时。
3. 业务错误：无练习配置、题目配置不合法。

后端保持统一响应结构，前端只做呈现，不做复杂业务判断。

## 10. 测试与验证

### 10.1 后端
- Service 层：公开通过、隐藏失败、超时、异常分支。
- Controller 层：接口契约与错误码。
- 与 sandbox 联调：最小集成测试覆盖主路径。

### 10.2 前端
- 关键交互验证：
  1) 加载练习成功
  2) 判题成功展示
  3) 判题失败可重试

## 11. 迭代建议（后续）

- V1.1：每课多题、难度分级。
- V1.2：题目标签与检索。
- V1.3：拆分 `exercise-service`，支撑更大题量与并发。

## 12. 验收标准（首版）

1. 每个 lesson 可配置并展示 1 题。
2. 用户提交代码后可获得公开明细 + 隐藏汇总。
3. PASS/FAIL 判定准确，且结果可回看最近一次提交。
4. 出错时前端可读提示明确，且不会清空用户代码。
5. 全链路在本地环境可稳定运行并通过基础测试。
