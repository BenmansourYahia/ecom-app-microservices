package net.yahia.kafkaspringcloudstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KafkaSpringCloudStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringCloudStreamApplication.class, args);
    }

}
