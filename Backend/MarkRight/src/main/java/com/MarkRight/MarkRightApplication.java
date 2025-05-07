package com.MarkRight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MarkRightApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkRightApplication.class, args);
	}

}
