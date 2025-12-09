package com.sist.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.sist.web.mapper")
@SpringBootApplication
public class SpringBootJspProject0Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJspProject0Application.class, args);
	}

}
