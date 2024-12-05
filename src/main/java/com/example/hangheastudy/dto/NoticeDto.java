package com.example.hangheastudy.dto;

import com.example.hangheastudy.common.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NoticeDto extends Timestamped {

    private Long userId;
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime regDt;
    private String regId;
    private LocalDateTime modDt;
    private String modId;
}
