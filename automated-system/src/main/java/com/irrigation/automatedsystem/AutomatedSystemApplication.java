package com.irrigation.automatedsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomatedSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatedSystemApplication.class, args);
	}
	

}
