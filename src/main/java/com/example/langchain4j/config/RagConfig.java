package com.example.langchain4j.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RagConfig {

//    // ✅ 定义一个内存型向量存储（等价于“内存版 Milvus”）
//    @Bean
//    public EmbeddingStore<TextSegment> embeddingStore() {
//        return new InMemoryEmbeddingStore<>();
//    }
//
//    // ✅ RAG 主配置逻辑（通过方法参数自动注入，不用 @Resource）
//    @Bean
//    public ContentRetriever contentRetriever(
//            EmbeddingModel qwenEmbeddingModel,
//            EmbeddingStore<TextSegment> embeddingStore
//    ) {
//
//        // 1️⃣ 加载文档
//        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/knowledge");
//
//        // 2️⃣ 分段切割
//        DocumentByParagraphSplitter paragraphSplitter = new DocumentByParagraphSplitter(1000, 200);
//
//        // 3️⃣ 生成嵌入并写入存储
//        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
//                .documentSplitter(paragraphSplitter)
//                .textSegmentTransformer(textSegment -> TextSegment.from(
//                        textSegment.metadata().getString("file_name") + "\n" + textSegment.text(),
//                        textSegment.metadata()
//                ))
//                .embeddingModel(qwenEmbeddingModel)
//                .embeddingStore(embeddingStore)
//                .build();
//
//        ingestor.ingest(documents);
//
//        // 4️⃣ 构造检索器（RAG 用于召回）
//        return EmbeddingStoreContentRetriever.builder()
//                .embeddingStore(embeddingStore)
//                .embeddingModel(qwenEmbeddingModel)
//                .maxResults(5)
//                .minScore(0.75)
//                .build();
//    }
}
