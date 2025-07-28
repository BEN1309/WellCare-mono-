package com.wellcare.service;

import com.wellcare.dto.AuthRequestDto;
import com.wellcare.dto.AuthResponseDto;
import com.wellcare.dto.UserDto;

public interface AuthService {

	AuthResponseDto registerUser(UserDto userDto);

	AuthResponseDto loginUser(AuthRequestDto requestDto);

}
