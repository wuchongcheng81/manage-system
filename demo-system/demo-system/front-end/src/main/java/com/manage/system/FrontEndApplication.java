package com.manage.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FrontEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontEndApplication.class, args);
    }

}
