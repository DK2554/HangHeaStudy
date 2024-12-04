package com.example.hangheastudy.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long noticeId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String noticeTitle;

    @Column(nullable = false)
    private String noticeContent;

    @Column(nullable = false)
    private String delYn;

    @Column(nullable = false)
    private String regId;

    @Column(nullable = false)
    private LocalDateTime regDt;

    @Column(nullable = false)
    private String modId;

    @Column(nullable = false)
    private LocalDateTime modDt;
}
