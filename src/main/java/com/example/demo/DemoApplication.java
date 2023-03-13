package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@SpringBootApplication
@Slf4j
public class   DemoApplication {

	public static void main(String[] args) {
		int exitCode =SpringApplication.exit( SpringApplication.run(DemoApplication.class, args));
		log.info("[Batch Application] Exit Code is " + exitCode);
		System.exit(exitCode);
	}

}
