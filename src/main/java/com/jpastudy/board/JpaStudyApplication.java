package com.jpastudy.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaStudyApplication.class, args);
    }

}
