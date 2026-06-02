package com.example.SpringAI.Imp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class promptTemplateService {

    private ChatClient chatClient;

    public promptTemplateService(@Qualifier("chatClient") ChatClient chatClient){

        this.chatClient = chatClient;
    }

    public String chatTemplate(String query){

        query = "what is {techName} tell me with examples of {exampleName}";


        // Created Template --------
        PromptTemplate strTemplate = PromptTemplate.builder().template(query).build();


        //Render template

       String renderMsg = strTemplate.render(Map.of(
                "techName","Spring",
                "exampleName","Spring boot"
        ));

       // create prompt

        Prompt prompt = new Prompt(renderMsg);


        return this.chatClient.prompt(prompt).system("As A JAVA EXPERT").call().content();
    }


}
