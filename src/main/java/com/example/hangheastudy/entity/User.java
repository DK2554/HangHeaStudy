package com.example.hangheastudy.entity;

import com.example.hangheastudy.common.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPasswd;

    @Column(nullable = false)
    private String delYn;

    @Column(nullable = false)
    private String regId;

    @Column(nullable = false)
    private String modId;

}

