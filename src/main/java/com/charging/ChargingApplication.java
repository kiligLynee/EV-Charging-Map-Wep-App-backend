package com.charging;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.charging.mapper")
public class ChargingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargingApplication.class, args);
    }

}
