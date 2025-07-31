package com.wellcare.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellcare.dto.AuthRequestDto;
import com.wellcare.dto.AuthResponseDto;
import com.wellcare.dto.UserDto;
import com.wellcare.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "User Register and Login APIs")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(authService.registerUser(userDto));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a user")
    public ResponseEntity<AuthResponseDto> login(
    		@RequestBody @Valid AuthRequestDto requestDto, 
    		HttpServletResponse response) {
    	
        AuthResponseDto authResponse = authService.loginUser(requestDto);
    	
    	//Create HTTP-only cookie with JWT Token
        Cookie jwtCookie = new Cookie("jwtToken", authResponse.getToken());
    	jwtCookie.setHttpOnly(true);
    	jwtCookie.setPath("/");   // cookie available for all endpoints
    	jwtCookie.setMaxAge(3600); // 1hr 
        
    	response.addCookie(jwtCookie);
    	
    	return ResponseEntity.ok(authResponse);
    }
}
