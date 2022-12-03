package com.example.productservice.auth;

import com.example.productservice.model.User;
import com.example.productservice.repositories.UserRepository;
import com.example.productservice.services.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsImplements implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(us -> new UserDetailsImpl(us.getId(),
                us.getUsername(), us.getPassword(), null)).orElseThrow(() -> new BadCredentialsException("User not found"));
    }
}