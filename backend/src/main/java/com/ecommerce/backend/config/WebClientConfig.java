package com.ecommerce.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    final String POKE_API = "https://pokeapi.co/api/v2";

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(POKE_API) // URL Base para no repetirla
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)) // Aumentar buffer
                                                                                                    // por si el JSON es
                                                                                                    // grande
                .build();
    }
}