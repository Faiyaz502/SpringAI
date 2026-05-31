package com.example.SpringAI.Imp;

import com.example.SpringAI.Entity.customResponse;
import com.example.SpringAI.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class chatServiceImp implements ChatService {


    private final ChatClient chatClient;

    public chatServiceImp(@Qualifier("chatClient") ChatClient chatClient) {

        this.chatClient = chatClient;
    }

    @Override
    public String chat(String query) {

        String promt = "tell me about adam grilcist";


        Prompt promt1 = new Prompt(promt);

//  ------------Ways -------------------


//     String content = chatClient
//                .prompt()
//                .user(promt)
//                .system("As a cricket Expert")
//                .call()
//                .content();


//   var content = chatClient
//           .prompt(promt1)
//           .call()
//           .content();



           var content = chatClient
                    .prompt(promt1)
                    .call()
                    .chatResponse()
                    .getResult()
                    .getOutput()
                    .getText();

      //-----For metaData--------------
           var metadata = chatClient
                   .prompt(promt1)
                   .call()
                   .chatResponse()
                   .getMetadata();


        System.out.println(metadata);


        //For Custom Entity Response



   return content;
    }
    // Single response by using prompt
    @Override
    public customResponse chatCustomResponse(String query) {

        String promt = "tell me the date of arafah in english calender";


        Prompt promt1 = new Prompt(promt);

        customResponse entity = chatClient
                .prompt(promt1)
                .call()
                .entity(customResponse.class);


        return entity;
    }
    //--------- Use for List Response---------
    @Override
    public List<customResponse> chatCustomResponseList(String query) {
        String promt = "who is adam grilcist";


        Prompt promt1 = new Prompt(promt);

        List<customResponse> entities = chatClient
                .prompt(promt1)
                .system("A cricket expert")
                .call()
                .entity(new ParameterizedTypeReference<List<customResponse>>() {
                });


        return entities;
    }

    // ---------Can make a Specific api call for a Specific AI model by using this

    @Override
    public String chatClientModelSpecific(String query) {

        Prompt prompt = new Prompt(query, OpenAiChatOptions.builder()
                .model("gemini-3.5-flash")
                .temperature(0.4)
                .maxTokens(100)
                .build());



        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }



    //--------Dynamic Prompt ----------



    @Override
    public String dynamicPrompt(String query) {

        //Traditional way
        String full_info = "Reply as a Coding expert in java. now Reply for this : {query}" ;
        var message = chatClient
                .prompt()
                .user(u -> u.text(full_info).param("query",query))
                .call()
                .content();



        return message ;
    }



}
