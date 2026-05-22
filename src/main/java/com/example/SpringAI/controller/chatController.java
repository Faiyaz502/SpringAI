package com.example.SpringAI.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class chatController {

    private final ChatClient geminiClient;
    private final ChatClient groqClient;
    private final ChatClient ollamaClient;

    public chatController(
            @Qualifier("geminiClient") ChatClient geminiClient,
            @Qualifier("groqClient") ChatClient groqClient,
            @Qualifier("ollamaClient") ChatClient ollamaClient
    ) {
        this.geminiClient = geminiClient;
        this.groqClient = groqClient;
        this.ollamaClient = ollamaClient;
    }


    @GetMapping("/chat/gemini")
    public ResponseEntity<String> chat(@RequestParam String q) {

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
}