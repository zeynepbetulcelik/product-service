package com.example.productservice.services.impl;

import com.example.productservice.auth.TokenManager;
import com.example.productservice.dto.UserDto;
import com.example.productservice.exception.AlreadyExist;
import com.example.productservice.exception.PasswordException;
import com.example.productservice.model.User;
import com.example.productservice.repositories.UserRepository;
import com.example.productservice.response.JwtResponse;
import com.example.productservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public JwtResponse registerUser(UserDto userDto) {
        if (checkUserExists(userDto.getUsername()))
            throw new AlreadyExist("User already exists with given email");
        if (userDto.getPassword().isEmpty())
            throw new PasswordException("Please fill the password field");
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encodePassword(userDto.getPassword()));
        user.setActive(true);
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        String jwt = tokenManager.generateJwtToken(userDetails);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtResponse(jwt);
    }

    @Override
    public JwtResponse logIn(UserDto userDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(userDto.getUsername(),
                            userDto.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        String jwtToken = tokenManager.generateJwtToken(userDetails);

        return new JwtResponse(jwtToken);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean checkUserExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
