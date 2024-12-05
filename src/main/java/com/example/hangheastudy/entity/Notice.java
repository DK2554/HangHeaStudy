package com.example.hangheastudy.entity;

import com.example.hangheastudy.common.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Notice extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long noticeId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Setter
    private String noticeTitle;

    @Column(nullable = false)
    @Setter
    private String noticeContent;

    @Column(nullable = false)
    @Setter
    private String delYn = "N"; // 기본값

    public void markAsDeleted() {
        this.delYn = "Y";
    }

    @Column(nullable = false)
    private String regId;

    @Column(nullable = false)
    private LocalDateTime regDt;

    @Column(nullable = false)
    @Setter
    private String modId;

    @Column(nullable = false)
    private LocalDateTime modDt;
}
