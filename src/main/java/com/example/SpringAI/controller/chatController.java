package com.example.SpringAI.controller;

import com.example.SpringAI.Entity.customResponse;
import com.example.SpringAI.service.ChatService;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class chatController {

    private final ChatClient geminiClient;
    private final ChatClient groqClient;
    private final ChatClient ollamaClient;



    // Structured Used

    private ChatService chatService;

    public chatController(
            @Qualifier("geminiClient") ChatClient geminiClient,
            @Qualifier("groqClient") ChatClient groqClient,
            @Qualifier("ollamaClient") ChatClient ollamaClient,
            ChatService chatService
    ) {
        this.geminiClient = geminiClient;
        this.groqClient = groqClient;
        this.ollamaClient = ollamaClient;
        this.chatService = chatService;
    }


    @GetMapping("/chat/gemini")
    public ResponseEntity<String> chatWithGemini(@RequestParam String q) {

        String response = geminiClient
                .prompt(q)
                .call()
                .content();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/chat/groq")
    public ResponseEntity<String> chatGroq(@RequestParam String q) {

        String response = groqClient
                .prompt(q)
                .call()
                .content();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/chat/local")
    public ResponseEntity<String> chatLocal(@RequestParam String q) {

        String response = ollamaClient
                .prompt(q)
                .call()
                .content();

        return ResponseEntity.ok(response);
    }


   /// Chat Response

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String q) {

        return ResponseEntity.ok(chatService.chat(q));
    }

    @GetMapping("/chat/entity")
    public ResponseEntity<customResponse> chatResponseEntity(@RequestParam String q) {

        return ResponseEntity.ok(chatService.chatCustomResponse(q));
    }

    @GetMapping("/chat/entities")
    public ResponseEntity<List<customResponse>> chatResponseEntities(@RequestParam String q) {

        return ResponseEntity.ok(chatService.chatCustomResponseList(q));
    }

}