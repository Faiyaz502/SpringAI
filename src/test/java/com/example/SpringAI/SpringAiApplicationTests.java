package com.example.SpringAI;

import com.example.SpringAI.Imp.ChatAdviserService;
import com.example.SpringAI.Imp.PrompReadingFromFiles;
import com.example.SpringAI.Imp.promptTemplateService;
import com.example.SpringAI.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiApplicationTests {

    @Autowired
    private ChatService chatService;

    @Autowired
    private PrompReadingFromFiles prompReadingFromFiles;

    @Autowired
    private promptTemplateService promptTemplateSerivce;

    @Autowired
    private ChatAdviserService chatAdviserService;

	@Test
	void contextLoads() {
	}

    @Test
    void dynamicPrompt(){

        System.out.println("Running the LLM");


        String res = chatService.dynamicPrompt("");


        System.out.println(res);


    }

    @Test
    void PromptUsingFile(){

        System.out.println("Running the LLM");


        String res = prompReadingFromFiles.UserPromptUsingFile();


        System.out.println(res);


    }

    @Test
    void chatTemplateWithSystemPrompt(){

        System.out.println("Running the LLM");


        String res = promptTemplateSerivce.chatTemplateWithSystemPrompt("");


        System.out.println(res);


    }

    @Test
    void chatTemplate(){

        System.out.println("Running the LLM ");
        String res = promptTemplateSerivce.chatTemplate("");

        System.out.println(res);

    }


    @Test
    void chatWithAdviser(){

        System.out.println("Running the LLM");

        String res = chatAdviserService.chatWithAdviser("Tell me about java Exceptions ");

        System.out.println(res);


    }

    @Test
    void chatClientModelSpecific(){

        System.out.println("Running the LLM ");

        String res = chatService.chatClientModelSpecific("Tell me about java And Spring boot ");

        System.out.println(res);


    }

    @Test
    void analyzeTokenConsumption(){

        System.out.println("Running the LLM");

        String res = chatAdviserService.chatWithAdviser("Tell me about Spring AI and one implementation");

        System.out.println(res);


    }

}
