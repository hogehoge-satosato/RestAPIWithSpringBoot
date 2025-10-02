package com.freelance.agent.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.freelance.agent.restapi.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class) 
public class SprintBootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintBootTestApplication.class, args);
    }

}
