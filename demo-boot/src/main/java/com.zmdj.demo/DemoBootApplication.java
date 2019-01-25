package com.zmdj.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class DemoBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBootApplication.class, args);
	}

}

