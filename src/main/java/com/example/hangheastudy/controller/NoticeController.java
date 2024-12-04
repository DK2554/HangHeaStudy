package com.example.hangheastudy.controller;

import com.example.hangheastudy.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NoticeController {

    @GetMapping("/api/notice/list")
    public ResponseEntity<Map<String, Object>> noticeList (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

    @GetMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> getNotice (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

    @PostMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> postNotice (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

    @PutMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> updateNotice (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

    @DeleteMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> deleteNotice (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }
}
