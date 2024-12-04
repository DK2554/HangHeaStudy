package com.example.hangheastudy.controller;

import com.example.hangheastudy.dto.UserDto;
import com.example.hangheastudy.service.UserService;
import com.example.hangheastudy.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, Object>> login (@RequestBody UserDto userDto){
        logger.info(userDto.toString());
        return ResponseBuilder.build(userService.getUser(userDto), HttpStatus.OK);
    }

    @PostMapping("/api/auth/sigup")
    public ResponseEntity<Map<String, Object>> sigup (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

}
