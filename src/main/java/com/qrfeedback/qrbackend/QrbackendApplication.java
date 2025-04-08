package com.qrfeedback.qrbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QrbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrbackendApplication.class, args);
	}

}
