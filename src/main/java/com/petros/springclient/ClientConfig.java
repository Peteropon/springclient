package com.petros.springclient;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ClientConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.setConnectTimeout(Duration.ofMillis(4000))
                .setReadTimeout(Duration.ofMillis(4000)).build();
    }

}
