package com.wellcare.actuator;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "customhealth")
public class CustomHealthEndPoint {

	@ReadOperation
	public Map<String, Object> customHealthCheck() {
		return Map.of(
				"status", "UP",
				"message", "WellCare custom health is OK",
				"version", "1.0.2"
				);
	}
	
}
