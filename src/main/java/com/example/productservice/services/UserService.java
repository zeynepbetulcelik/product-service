package com.example.productservice.services;

import com.example.productservice.dto.UserDto;
import com.example.productservice.response.JwtResponse;

public interface UserService {

    JwtResponse registerUser(UserDto userDto);

    JwtResponse logIn(UserDto userDto) throws Exception;
}
