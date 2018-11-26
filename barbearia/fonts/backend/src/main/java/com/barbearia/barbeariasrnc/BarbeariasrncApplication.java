package com.barbearia.barbeariasrnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BarbeariasrncApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarbeariasrncApplication.class, args);
	}
}
