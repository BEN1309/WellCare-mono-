package com.wellcare.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info()
						.title("WellCare API")
						.description("API documentation for WellCare monolithic application").version("v1.0"));
	}

	@Bean
	GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("wellcare")
				.pathsToMatch("/api/**")
				.packagesToScan("com.wellcare.controllers")
				.build();
	}
}
