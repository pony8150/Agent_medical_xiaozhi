# 医疗小智（Medical-Assistant-Xiaozhi）

**项目简介**
“医疗小智”是一款面向医疗垂直领域的智能伴诊系统，基于 LangChain4j + Qwen 大模型构建，融合 RAG 与 Agent 架构。系统不仅提供权威的医疗知识问答，还能通过工具链自主完成挂号、查询等业务操作，实现从理解需求到执行任务的完整闭环。
本人在项目中主要负责大模型 Agent 流程编排、RAG 检索模块、工具链调用与后端服务的整体开发与优化工作。


## 技术栈

* **后端**：Spring Boot、WebFlux
* **大模型**：Qwen（DashScope）
* **智能体**：LangChain4j（Agent + Function Calling）
* **向量库**：Pinecone
* **数据库**：MySQL、MongoDB



## 核心功能与职责（精简版）

### 1. Agent 架构 & 大模型集成

* 基于 LangChain4j 集成 Qwen，构建“医疗顾问”智能体。
* 通过 Prompt Engineering 完成意图识别、任务拆解与多轮对话。

### 2. RAG 检索增强

* 使用 Pinecone 构建医疗知识向量库。
* 通过分段策略 + 文档预处理减少幻觉，提升专业问答效果。

### 3. Function Calling 工具链

* 开发挂号相关功能：查询号源、创建订单、查询/取消预约。
* 实现从问诊到业务处理的完整执行链路。

### 4. 对话记忆

* 基于 MongoDB 存储会话记忆，实现连续多轮问诊。
* 结合 WebFlux 支持高并发与流式回复。




## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/pony8150/Agent_medical_xiaozhi.git
cd Agent_medical_xiaozhi
```



## 2. 配置 

### Qwen API Key

修改：

```
langchain4j/src/main/resources/application-local.yml
```

```yaml
langchain4j:
  community:
    dashscope:
      chat-model:
        model-name: qwen-plus
        api-key: <你的Qwen API Key>
```

### Pinecone API Key

修改：

```
langchain4j/config/EmbeddingStoreConfig.java
```

```java
return PineconeEmbeddingStore.builder()
        .apiKey("<你的Pinecone API Key>")
        .build();
```



## 3. 启动后端

使用 IDEA 运行：

```
Langchain4jApplication
```



## 4. 启动前端，交互即可 

```bash
cd langchain4j/src/main/resources/xiaozhi-ui
npm install
npm run dev
```

前端访问地址：

```
http://localhost:5174/
```


