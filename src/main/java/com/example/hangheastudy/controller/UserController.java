package com.example.hangheastudy.controller;

import com.example.hangheastudy.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, Object>> login (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

    @PostMapping("/api/auth/sigup")
    public ResponseEntity<Map<String, Object>> sigup (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

}
