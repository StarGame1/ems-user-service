package com.emsuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmsUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsUserServiceApplication.class, args);
    }

}
