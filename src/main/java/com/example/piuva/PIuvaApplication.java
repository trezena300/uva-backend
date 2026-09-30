package com.example.piuva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PIuvaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PIuvaApplication.class, args);
    }

}
