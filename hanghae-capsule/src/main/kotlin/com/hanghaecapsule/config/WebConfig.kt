package com.hanghaecapsule.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // TODO 2023-03-21 경록: 임시로 설정
            .allowedOrigins(
                "http://localhost:3000",
                "https://6427abd087d8903adeb37bb5--mellifluous-donut-b483a5.netlify.app",
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowCredentials(false)
            .maxAge(3600)
    }
}
