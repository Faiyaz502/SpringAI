package com.example.SpringAI.Imp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
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
                "exampleName","Spring Exception"
        ));

       // create prompt

        Prompt prompt = new Prompt(renderMsg);


        return this.chatClient.prompt(prompt).system("As A JAVA EXPERT").call().content();
    }

    //Add Dynamic System Prompt


    public String chatTemplateWithSystemPrompt(String query){

        query = "what is {techName} tell me with examples of {exampleName}";


       var SystemTemplate = SystemPromptTemplate.builder().template("You are a expert and assistant of java").build();

       Message systemMessage = SystemTemplate.createMessage();

       var userPromt = PromptTemplate.builder().template(query).build();


        Message userMsg = userPromt.createMessage(Map.of(
                "techName","Spring",
                "exampleName","Spring Exception"
        ));

        // create prompt

        Prompt prompt = new Prompt(userMsg,systemMessage);


        return this.chatClient.prompt(prompt).call().content();
    }


}
