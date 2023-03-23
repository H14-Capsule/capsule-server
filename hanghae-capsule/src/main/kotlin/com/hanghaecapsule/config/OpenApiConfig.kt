package com.hanghaecapsule.config

import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun api(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("api")
            .addOpenApiCustomiser {
                it
                    .info(
                        Info()
                            .title("항해 캡슐")
                            .description("항해 캡슐 API 문서")
                            .version("1.0")
                    )
            }
            .pathsToMatch("/**")
            .build()
    }
}
