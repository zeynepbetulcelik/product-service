package com.example.productservice.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String jwtToken;

}