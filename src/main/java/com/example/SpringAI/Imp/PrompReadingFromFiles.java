package com.example.SpringAI.Imp;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class PrompReadingFromFiles {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user_message.st")
    private Resource userMessage;

    public PrompReadingFromFiles(@Qualifier("chatClient") ChatClient chatClient){

        this.chatClient = chatClient;
    }


    public String UserPromptUsingFile(){

        return chatClient
                .prompt()
                .system(promptSystemSpec -> promptSystemSpec.text("Java Expert"))
                .user(user -> user.text(userMessage)
                        .param("concept","Spring Framework"))
                .call()
                .content();

    }


}
