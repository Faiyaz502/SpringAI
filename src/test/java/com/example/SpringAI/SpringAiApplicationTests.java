package com.example.SpringAI;

import com.example.SpringAI.Imp.PrompReadingFromFiles;
import com.example.SpringAI.Imp.promptTemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiApplicationTests {


    @Autowired
    private PrompReadingFromFiles prompReadingFromFiles;

    @Autowired
    private promptTemplateService promptTemplateSerivce;

	@Test
	void contextLoads() {
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

        System.out.println("Running the LLM");


        String res = promptTemplateSerivce.chatTemplate("");


        System.out.println(res);


    }

}
