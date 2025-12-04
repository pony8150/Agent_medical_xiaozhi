package com.example.langchain4j.controller;

import com.example.langchain4j.entity.ChatFormDTO;
import com.example.langchain4j.service.SeparateChatAssistant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController // ← 必须加
public class XiaozhiController {

    @Autowired
    private SeparateChatAssistant assistant;

    @Operation(summary = "对话")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatFormDTO chatFormDTO) {
        return assistant.chat(chatFormDTO.getMemoryId(), chatFormDTO.getUserMessage());
    }

    @Operation(summary = "流式对话")
    @PostMapping(value = "/chatFlux",produces = "text/stream;charset=utf-8")
    public Flux<String> chatWithFlux(@RequestBody ChatFormDTO chatFormDTO) {
        return assistant.chatWithFlux(chatFormDTO.getMemoryId(), chatFormDTO.getUserMessage());
    }


    @PostMapping("/hello")
    public String hello(@RequestBody ChatFormDTO chatFormDTO) {
//            return assistant.chat(chatFormDTO.getMemoryId(), chatFormDTO.getUserMessage());
//        return "hello world";
        return chatFormDTO.getUserMessage();
    }
}
