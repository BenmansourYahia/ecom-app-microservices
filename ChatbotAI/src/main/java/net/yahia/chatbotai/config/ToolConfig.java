package net.yahia.chatbotai.config;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class ToolConfig {

    @Bean
    public ToolCallbackProvider toolCallbackProvider() {
        // Retourne un provider vide pour l'instant
        // Plus tard, vous pourrez ajouter des outils personnalisés ici
        return new ToolCallbackProvider() {
            @Override
            public ToolCallback[] getToolCallbacks() {
                return new ToolCallback[0];
            }
        };
    }
}
