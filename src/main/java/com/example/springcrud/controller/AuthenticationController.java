package com.example.springcrud.controller;

import com.example.springcrud.dto.AuthenticationRequest;
import com.example.springcrud.dto.AuthenticationResponse;
import com.example.springcrud.dto.RegistrationRequest;
import com.example.springcrud.entity.UserEntity;
import com.example.springcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/authentication")
    public AuthenticationResponse authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authenticate(authenticationRequest);
    }

    @PostMapping("/registration")
    public AuthenticationResponse registration(@RequestBody RegistrationRequest registrationRequest) {
        return userService.register(registrationRequest);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<UserEntity> activate(@PathVariable String code) {
        return new ResponseEntity<>(userService.activateUser(code), HttpStatus.CREATED);
    }
}
//