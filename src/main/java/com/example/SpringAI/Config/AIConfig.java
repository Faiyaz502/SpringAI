package com.example.SpringAI.Config;

import com.example.SpringAI.adviser.TokenTraceAdviser;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;

import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AIConfig {

    @Bean("geminiClient")
    public ChatClient geminiClient() {

        OpenAiApi api = OpenAiApi.builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/openai")
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .build();

        OpenAiChatModel model = OpenAiChatModel.builder()
                .openAiApi(api)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("gemini-3.5-flash")
                        .build())
                .build();

        return ChatClient.builder(model)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Bean("groqClient")
    public ChatClient groqClient() {

        OpenAiApi api = OpenAiApi.builder()
                .baseUrl("https://api.groq.com/openai")
                .apiKey(System.getenv("GROQ_API_KEY"))
                .build();

        OpenAiChatModel model = OpenAiChatModel.builder()
                .openAiApi(api)
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("qwen/qwen3-32b")
                        .build())
                .build();

        return ChatClient.builder(model).build();
    }

    @Bean("ollamaClient")
    public ChatClient ollamaClient(OllamaChatModel ollamaChatModel) {

        return ChatClient.builder(ollamaChatModel).build();
    }


    @Bean("chatClient")
    public ChatClient chatClient() {

        OpenAiApi api = OpenAiApi.builder()
                .baseUrl("https://api.groq.com/openai")
                .apiKey(System.getenv("GROQ_API_KEY"))
                .build();

        OpenAiChatModel model = OpenAiChatModel.builder()
                .openAiApi(api)
                .defaultOptions(OpenAiChatOptions.builder()
                        .maxTokens(200)
                        .model("qwen/qwen3-32b")
                        .build())
                .build();

        return ChatClient.builder(model)
                .defaultAdvisors(new SafeGuardAdvisor(List.of("Game","Cricket","Football","Job")),
                        new TokenTraceAdviser()) // globally will log all thing and ignore the prompt with those words
                .defaultSystem("You are a professional assistant of Sports!!!")
                .build();
    }
}