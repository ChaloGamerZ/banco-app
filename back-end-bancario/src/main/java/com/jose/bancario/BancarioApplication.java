package com.jose.bancario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jose.bancario")
public class BancarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancarioApplication.class, args);
	}

}
