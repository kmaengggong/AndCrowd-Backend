package com.fiveis.andcrowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.fiveis.andcrowd.entity")
public class AndcrowdApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndcrowdApplication.class, args);
	}

}
