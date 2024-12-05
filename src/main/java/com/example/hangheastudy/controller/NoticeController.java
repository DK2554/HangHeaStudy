package com.example.hangheastudy.controller;

import com.example.hangheastudy.dto.NoticeDto;
import com.example.hangheastudy.service.NoticeService;
import com.example.hangheastudy.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/api/notice/list")
    public ResponseEntity<Map<String, Object>> noticeList (){
        return ResponseBuilder.build(noticeService.list(), HttpStatus.OK);
    }

    @GetMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> getNotice (@PathVariable Long id){
        return ResponseBuilder.build(noticeService.getNotice(id), HttpStatus.OK);
    }

    @PostMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> postNotice (HttpServletRequest request){
        return ResponseBuilder.build(new HashMap<>(), HttpStatus.OK);
    }

    @PutMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> updateNotice (@PathVariable Long id, @RequestBody NoticeDto noticeDto){
        return ResponseBuilder.build(noticeService.updateNotice(id, noticeDto), HttpStatus.OK);
    }

    @DeleteMapping("/api/notice/{id}")
    public ResponseEntity<Map<String, Object>> deleteNotice (@PathVariable Long id){
        return ResponseBuilder.build(noticeService.deleteNotice(id), HttpStatus.OK);
    }
}
