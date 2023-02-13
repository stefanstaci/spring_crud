package com.example.springcrud.controller;

import com.example.springcrud.dto.AuthenticationRequest;
import com.example.springcrud.dto.AuthenticationResponse;
import com.example.springcrud.dto.RegistrationRequest;
import com.example.springcrud.entity.UserEntity;
import com.example.springcrud.security.JWTService;
import com.example.springcrud.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final JWTService jwtService;

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest,
                                                 HttpServletResponse httpResponse) {
        AuthenticationResponse response = userService.authenticate(authenticationRequest);
        String token = jwtService.generateToken(new UserEntity(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        Cookie cookie = new Cookie("token", token);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);
        return ResponseEntity.ok(response);

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