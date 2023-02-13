package com.example.springcrud.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String Password;
}
