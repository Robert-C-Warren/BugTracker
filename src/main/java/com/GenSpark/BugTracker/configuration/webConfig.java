package com.GenSpark.BugTracker.configuration;

import jdk.jfr.Enabled;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class webConfig implements WebMvcConfigurer {
    @Override // allow Cross from any website requesting Get Post Put and Delete
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DElETE");
    }
}
