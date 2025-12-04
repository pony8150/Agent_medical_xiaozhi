package com.example.langchain4j.entity;

import lombok.Data;

@Data
public class ChatFormDTO {
    //会话id
    private int memoryId;
    //用户消息
    private String userMessage;
}