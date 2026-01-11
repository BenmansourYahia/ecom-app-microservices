package net.yahia.chatbotai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@org.springframework.cloud.openfeign.EnableFeignClients
public class ChatbotAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatbotAiApplication.class, args);
    }

}
