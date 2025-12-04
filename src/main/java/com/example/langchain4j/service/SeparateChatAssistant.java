package com.example.langchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;


@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
//        chatModel = "openAiChatModel",//找到对应的bean进行绑定
        streamingChatModel = "qwenStreamingChatModel",
        //chatMemory = "chatMemory",//找到对应的bean进行绑定
        chatMemoryProvider = "chatMemoryProvider",//找到对应的bean进行绑定
        tools = "appointmentTools"//找到对应的bean进行绑定
        , contentRetriever = "contentRetrieverPinecone"
)

public interface SeparateChatAssistant {

    //    @SystemMessage("你是一个智能助手，请用湖南话回答问题。")
    @SystemMessage(fromResource = "xiaozhi-prompt-template.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @SystemMessage(fromResource = "xiaozhi-prompt-template.txt")
    Flux<String> chatWithFlux(@MemoryId int memoryId, @UserMessage String userMessage);

}
