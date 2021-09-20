package com.example.onboarding.poc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.onboarding.poc2")
@EntityScan("com.example.onboarding.poc2.model")
@EnableJpaRepositories("com.example.onboarding.poc2.dao")
public class Poc2Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Poc2Application.class, args);
	}

}
