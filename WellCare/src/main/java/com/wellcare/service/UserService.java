package com.wellcare.service;

import com.wellcare.dto.UserDto;
import com.wellcare.entities.User;

public interface UserService {

	User saveUser(UserDto userDto);
    User findByUsername(String username);
	
}
