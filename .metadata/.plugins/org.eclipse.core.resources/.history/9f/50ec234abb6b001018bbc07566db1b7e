package com.wellcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.wellcare.entities")
@EnableJpaRepositories("com.wellcare.repositories")
public class WellCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellCareApplication.class, args);
		System.out.println("Application Started");
	}

}
