package com.example.SpringAI.adviser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

public class TokenTraceAdviser implements StreamAdvisor , CallAdvisor {

    private final Logger log = LoggerFactory.getLogger(TokenTraceAdviser.class);


    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {


        this.log.info("---------My Token Adviser Called---------");

        this.log.info("Request : ---"+ chatClientRequest.prompt().getContents());

        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        this.log.info("---------Respose got from the Model---------");
        this.log.info("Response : "+ chatClientResponse.chatResponse().getResult().getOutput().getText());

        //Prompt Token-----
        this.log.info("Prompt Token Consumed : ------->"+chatClientResponse
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getPromptTokens());

        //Completion Token -----------

        this.log.info("Completion Token Consumed : ------->"+chatClientResponse
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getCompletionTokens());


        //Total Token -----------

        this.log.info("Total Token Consumed :------->"+chatClientResponse
                .chatResponse()
                .getMetadata()
                .getUsage()
                .getTotalTokens());



        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return streamAdvisorChain.nextStream(chatClientRequest);
    }

    @Override
    public String getName() {

        return this.getClass().getName();

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
