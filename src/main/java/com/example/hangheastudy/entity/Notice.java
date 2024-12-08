package com.example.hangheastudy.entity;

import com.example.hangheastudy.common.Timestamped;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED) // Builder와 충돌 방지
@Builder
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

    @Column(nullable = true)
    @Setter
    private String modId;

    @Column(nullable = false)
    private LocalDateTime modDt;

    @PrePersist
    public void prePersist() {
        this.regDt = LocalDateTime.now(); // 현재 시간 설정
        this.modDt = LocalDateTime.now(); // 처음 생성 시 수정 시간도 동일하게 설정
    }

    @PreUpdate
    public void preUpdate() {
        this.modDt = LocalDateTime.now(); // 수정 시간 갱신
    }


}
