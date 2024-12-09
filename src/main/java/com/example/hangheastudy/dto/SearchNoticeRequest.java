package com.example.hangheastudy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SearchNoticeRequest {
    private String noticeTitle;  // 제목
    private String regId;        // 작성자 ID
    private LocalDate regDt;     // 작성 날짜
}