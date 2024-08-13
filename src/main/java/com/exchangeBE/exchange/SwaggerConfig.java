package com.exchangeBE.exchange;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("교환하자")
                .description("교환하자 관련 명세서")
                .version("v0.0.1");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
