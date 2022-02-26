package com.sparta.juteukki02.juteukki_week02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JuteukkiWeek02Application {

    public static void main(String[] args) {
        SpringApplication.run(JuteukkiWeek02Application.class, args);
    }



}
