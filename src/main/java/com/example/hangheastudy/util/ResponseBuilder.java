package com.example.hangheastudy.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public final class ResponseBuilder {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String CHARSET_UTF8 = ";charset=UTF-8";

    private ResponseBuilder() {
        throw new UnsupportedOperationException();
    }

    public static ResponseEntity<Map<String, Object>> build(Map<String, Object> resMap, HttpStatus httpStatus) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE + CHARSET_UTF8);
        Map<String, Object> httpBody = new HashMap<>();
        httpBody.put("resultCode", "0000");
        httpBody.put("resultMsg", "SUCCESS");
        httpBody.put("resultData", resMap);
        return new ResponseEntity<>(httpBody, httpHeaders, httpStatus);
    }
}
