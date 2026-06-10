package com.example.SpringAI.Imp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatAdviserService {


        private final ChatClient chatClient;

        public ChatAdviserService(@Qualifier("chatClient") ChatClient chatClient){

            this.chatClient = chatClient;
        }


        public String chatWithAdviser(String query){

           String res = chatClient
                    .prompt(query)
                    .advisors(new SimpleLoggerAdvisor())
                    .call()
                    .content();



            return res ;
        }


}
