package com.kabu.dev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@MapperScan("com.kabu.dev.dao")
public class KaBuApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaBuApplication.class, args);
	}

}
