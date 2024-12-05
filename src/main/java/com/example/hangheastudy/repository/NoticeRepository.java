package com.example.hangheastudy.repository;

import com.example.hangheastudy.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository <Notice, Long> {
}
