package com.feng.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        // 解决redis 和 es 使用不同的netty版本问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(UserApplication.class, args);
    }

}
