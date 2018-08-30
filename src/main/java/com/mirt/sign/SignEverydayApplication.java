package com.mirt.sign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Mirt
 */

@SpringBootApplication
@MapperScan("com.mirt.sign.dao")
@EntityScan("com.mirt.sign.model")
@ServletComponentScan
@EnableRedisHttpSession
public class SignEverydayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignEverydayApplication.class, args);
    }
}
