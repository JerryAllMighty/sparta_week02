package com.sparta.juteukki02.juteukki_week02;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {
    @Override public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
        registry.addMapping("/**").allowedOrigins("https://spartaweek2-25f73.firebaseapp.com/");
        registry.addMapping("/**").allowedOrigins("https://magazine-409bc.web.app/");
        registry.addMapping("/**").allowedOrigins("http://localhost:3000");

    }}
