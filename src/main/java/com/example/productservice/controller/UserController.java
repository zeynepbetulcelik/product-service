package com.example.productservice.controller;

import com.example.productservice.dto.UserDto;
import com.example.productservice.response.JwtResponse;
import com.example.productservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> logIn(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.logIn(userDto));
    }
}
