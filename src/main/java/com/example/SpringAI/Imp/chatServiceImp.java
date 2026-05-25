package com.example.SpringAI.Imp;

import com.example.SpringAI.Entity.customResponse;
import com.example.SpringAI.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
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


}
