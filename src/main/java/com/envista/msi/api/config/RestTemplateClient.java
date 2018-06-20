package com.envista.msi.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by KRISHNAREDDYM on 6/20/2018.
 */
@Configuration
public class RestTemplateClient {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}