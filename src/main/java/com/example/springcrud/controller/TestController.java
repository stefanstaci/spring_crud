package com.example.springcrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/testcontroller")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("is working");
    }
}
