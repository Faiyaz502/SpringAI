package com.example.SpringAI;

import com.example.SpringAI.Imp.PrompReadingFromFiles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiApplicationTests {


    @Autowired
    private PrompReadingFromFiles prompReadingFromFiles;

	@Test
	void contextLoads() {
	}
    @Test
    void PromptUsingFile(){

        System.out.println("Running the LLM");


        String res = prompReadingFromFiles.UserPromptUsingFile();


        System.out.println(res);


    }

}
