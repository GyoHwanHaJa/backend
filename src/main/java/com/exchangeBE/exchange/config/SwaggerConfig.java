package com.exchangeBE.exchange.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("GyoHwanHaJa API")
                        .description("GyoHwanHaJa Application API documentation")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("GyoHwanHaJa Application API documentation")
                        .url("https://github.com/GyoHwanHaJa/backend"));
    }
}
