package com.wjcwleklinski.worktimemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkTimeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkTimeManagerApplication.class, args);
	}

	@Bean
	public Logger myLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}

}
