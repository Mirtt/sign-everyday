package com.mirt.sign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@MapperScan("com.mirt.sign.dao")
@EntityScan("com.mirt.sign.model")
public class SignEverydayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignEverydayApplication.class, args);
    }
}
