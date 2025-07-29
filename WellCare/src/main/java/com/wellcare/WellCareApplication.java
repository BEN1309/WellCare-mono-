package com.wellcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.wellcare.config.JwtProperties;

@SpringBootApplication
@EntityScan("com.wellcare.entities")
@EnableJpaRepositories("com.wellcare.repositories")
@EnableConfigurationProperties(JwtProperties.class)
@EnableCaching
public class WellCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellCareApplication.class, args);
		System.out.println("Application Started");
	}

}
