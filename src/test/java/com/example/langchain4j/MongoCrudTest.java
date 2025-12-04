package com.example.langchain4j;

import com.example.langchain4j.entity.ChatMessages;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MongoCrudTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testCreateChatMessage() {
//        ChatMessages chatMessage = new ChatMessages();
//        chatMessage.setMessageId(new ObjectId());
//        chatMessage.setContent("{\"message\":\"Hello, world!\"}");
//
//        ChatMessages savedMessage = mongoTemplate.save(chatMessage);
//        assertNotNull(savedMessage.getMessageId());
//        System.out.println("Created ChatMessage: " + savedMessage);
    }

    @Test
    public void testReadChatMessage() {
        ChatMessages chatMessage = mongoTemplate.findById(new ObjectId("692955d40fff8d1c527aca0c"), ChatMessages.class);
        assertNotNull(chatMessage);
        System.out.println("Read ChatMessage: " + chatMessage);
    }

    @Test
    public void testUpdateChatMessage() {
        ObjectId id = new ObjectId(); // Replace with an existing ID in your database
        ChatMessages chatMessage = mongoTemplate.findById(new ObjectId("692955d40fff8d1c527aca0c"), ChatMessages.class);
        assertNotNull(chatMessage);

        chatMessage.setContent("{\"message\":\"Updated content\"}");
        ChatMessages updatedMessage = mongoTemplate.save(chatMessage);
        assertEquals("{\"message\":\"Updated content\"}", updatedMessage.getContent());
        System.out.println("Updated ChatMessage: " + updatedMessage);
    }

    @Test
    public void testDeleteChatMessage() {
        ObjectId id = new ObjectId(); // Replace with an existing ID in your database
        ChatMessages chatMessage = mongoTemplate.findById(id, ChatMessages.class);
        assertNotNull(chatMessage);

        mongoTemplate.remove(chatMessage);
        ChatMessages deletedMessage = mongoTemplate.findById(id, ChatMessages.class);
        assertNull(deletedMessage);
        System.out.println("Deleted ChatMessage with ID: " + id);
    }



}