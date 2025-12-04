package com.example.langchain4j;

import com.example.langchain4j.service.Assistant;
import com.example.langchain4j.service.MemoryChatAssistant;
import com.example.langchain4j.service.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.*;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class Langchain4jApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void testGPTDemo() {
        //初始化模型
        OpenAiChatModel model = OpenAiChatModel.builder()
                //LangChain4j提供的代理服务器，该代理服务器会将演示密钥替换成真实密钥， 再将请求转发给OpenAI API
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1") //设置模型api地址（如果apiKey="demo"，则可省略baseUrl的配置）
                .apiKey("sk-a69f288701c64b568d40f4ce84185373") //设置模型apiKey
                .modelName("qwen-plus") //设置模型名称
                .build();

        //向模型提问
        String answer = model.chat("你好");
        //输出结果
        System.out.println(answer);
    }


    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    public void testSpringBoot() {
        //向模型提问
        String answer = openAiChatModel.chat("你好，你是什么模型？");
        //输出结果
        System.out.println(answer);
    }


    @Autowired
    private OllamaChatModel ollamaChatModel;
    @Test
    public void testOllama() {
        //向模型提问
        String answer = ollamaChatModel.chat("你好 你是什么模型？");
        //输出结果
        System.out.println(answer);
    }



    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testDashScopeQwen() {
        //向模型提问
        String answer = qwenChatModel.chat("你好");
        //输出结果
        System.out.println(answer);
    }



    @Autowired
    private Assistant assistant;

    @Test
    public void testAssistant() {
        String answer = assistant.chat("Hello");
        System.out.println(answer);
    }


    @Test
    public void testChatMemory3() {
        //创建chatMemory
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        //创建AIService
        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        //调用service的接口
        String answer1 = assistant.chat("我是环环");
        System.out.println(answer1);
        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);
    }


    @Autowired
    private MemoryChatAssistant memoryChatAssistant;

    @Test
    public void testChatMemory4() {
        String answer1 = memoryChatAssistant.chat("我是环环");
        System.out.println(answer1);
        String answer2 = memoryChatAssistant.chat("我是谁");
        System.out.println(answer2);
    }


    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testChatMemory5() {

        String answer1 = separateChatAssistant.chat(1, "我是环环");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(1, "我是谁");
        System.out.println(answer2);
        String answer3 = separateChatAssistant.chat(2, "我是谁");
        System.out.println(answer3);

    }

    @Test
    public void testSystemMessage() {
        String answer = separateChatAssistant.chat(3, "今天几号");

        System.out.println(answer);
    }

    @Test
    public void testCalculatorTools() {

        String answer = separateChatAssistant.chat(4, "1+2等于几，475695037565的平方根是多少？");

        System.out.println(answer);

    }


    @Test
    public void testCalculatorTools1() {

        String answer = separateChatAssistant.chat(11, "我要挂今天下午妇产科的号，我叫刘波，身份证号是：000000000000000000");

        System.out.println(answer);

    }


    @Test
    public void testParsePDF() {
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/医院信息.pdf",
                new ApachePdfBoxDocumentParser());
        System.out.println(document);
    }


    @Test
    public void testReadDocumentAndStore() {
        // 加载文档
        Document document = FileSystemDocumentLoader.loadDocument("src/main/resources/knowledge/人工智能.md");
        // 创建内存向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        // 文档分割与嵌入生成
        EmbeddingStoreIngestor.ingest(document, embeddingStore);
        // 查看向量存储内容
        System.out.println(embeddingStore);
    }

    @Autowired
    private EmbeddingModel embeddingModel;//注入千问embeddingModel

    @Test
    public void testEmbeddingModel() {

        Response<Embedding> embed = embeddingModel.embed("你好");

        System.out.println("向量维度：" + embed.content().vector().length);
        System.out.println("向量输出：" + embed.toString());

    }


    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;
    @Test
    public void testPineconeEmbeddingStore() {

        TextSegment segment1 = TextSegment.from("我喜欢羽毛球");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("今天天气很好");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }


    @Test
    public void testEmbeddingSearch(){
        Embedding queryEmbedding = embeddingModel.embed("你最喜欢的运动是什么？").content();

        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);

        System.out.println("匹配的分数：" + embeddingMatch.score());
        System.out.println("匹配的内容：" + embeddingMatch.embedded().text());
    }
}
