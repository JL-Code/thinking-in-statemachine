package com.jiangy.thinkinginstatemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class ThinkingInStatemachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThinkingInStatemachineApplication.class, args);
    }

}
