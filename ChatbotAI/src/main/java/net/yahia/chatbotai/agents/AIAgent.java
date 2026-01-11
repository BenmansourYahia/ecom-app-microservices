package net.yahia.chatbotai.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Component
public class AIAgent {
    private ChatClient chatClient ;

    public AIAgent(ChatClient.Builder builder,
                   ChatMemory memory, ToolCallbackProvider tools) {
        Arrays.stream(tools.getToolCallbacks()).forEach(toolCallback ->{
            System.out.println("----------------------");
            System.out.println(toolCallback.getToolDefinition());
            System.out.println("----------------------");
        });
        this.chatClient= builder
                .defaultSystem("Vous etes un agent qui se charge de répondre aux questions des users en fonction de contexte fourni .Si aucun contexte n est donne répond avec je ne sais pas")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .defaultToolCallbacks(tools)
                .build();
    }
    @GetMapping( "/chat")
    public String askAgent(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
