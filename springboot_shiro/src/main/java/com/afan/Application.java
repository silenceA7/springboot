package com.afan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 * Create By User on 2020/8/24
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.afan.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
