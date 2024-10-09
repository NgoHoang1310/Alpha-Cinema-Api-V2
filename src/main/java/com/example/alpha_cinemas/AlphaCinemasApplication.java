package com.example.alpha_cinemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AlphaCinemasApplication {

    public static void main(String[] args) {

        SpringApplication.run(AlphaCinemasApplication.class, args);
    }

}
