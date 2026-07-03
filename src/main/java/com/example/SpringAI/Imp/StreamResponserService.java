package com.example.SpringAI.Imp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StreamResponserService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user_message.st")
    Resource userMessage ;

    public StreamResponserService(@Qualifier("chatClient") ChatClient chatClient) {

        this.chatClient = chatClient;
    }

    public Flux<String> chatStream(String query){

        return this.chatClient.prompt()
                .system(promptSystemSpec -> promptSystemSpec.text("Java Expert"))
                .stream()
                .content();



    }


}
