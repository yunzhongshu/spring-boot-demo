package com.zmdj.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.zmdj.db.dao.mapper"})
@ComponentScan(basePackages = {"com.zmdj.db"})
public class DBBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DBBootApplication.class, args);
	}

}

