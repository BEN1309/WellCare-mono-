package com.wellcare.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellcare.dto.DoctorDto;
import com.wellcare.service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

	private final DoctorService doctorService;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto) {
		DoctorDto savedDoctor = doctorService.createDoctor(doctorDto);
		return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
	}
	
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable String id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<DoctorDto> updateDoctor(
            @PathVariable String id,
            @Valid @RequestBody DoctorDto doctorDto) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctorDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
    
    // @SuppressWarnings("SpringWebAnnotations") // for Java Compiler 
	@GetMapping("/me")
    public ResponseEntity<String> getUserFromCookie(
    		@CookieValue(name = "token", required = false) String token){
    	if (token == null || token.isBlank()) {
    		
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    				.body("No Token Found");
    	}
    	return ResponseEntity.ok("Token : " +token);
    }
    
}
