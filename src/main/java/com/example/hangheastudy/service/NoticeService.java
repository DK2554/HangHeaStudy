package com.example.hangheastudy.service;

import com.example.hangheastudy.dto.NoticeDto;
import com.example.hangheastudy.entity.Notice;
import com.example.hangheastudy.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> list() {
        return noticeRepository.findAll();
    }

    public NoticeDto getNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));
        return NoticeDto
                .builder()
                .noticeId(notice.noticeId)
                .userId(notice.getUserId())
                .noticeContent(notice.getNoticeContent())
                .noticeTitle(notice.getNoticeTitle())
                .regDt(notice.getRegDt())
                .regId(notice.getRegId())
                .build();

    }

    @Transactional
    public NoticeDto updateNotice(Long id, NoticeDto noticeDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));

        notice.setNoticeTitle(noticeDto.getNoticeTitle());
        notice.setNoticeContent(noticeDto.getNoticeContent());
        notice.setModId(notice.getRegId());

        return NoticeDto
                .builder()
                .noticeId(notice.noticeId)
                .userId(notice.getUserId())
                .noticeContent(notice.getNoticeContent())
                .noticeTitle(notice.getNoticeTitle())
                .regDt(notice.getRegDt())
                .regId(notice.getRegId())
                .modId(notice.getModId())
                .build();
    }

    @Transactional
    public Map<String, Object> deleteNotice(Long id) {
        Map<String, Object> resultMap = new HashMap<>();
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 방법 1: 직접 엔티티 필드 수정
        notice.markAsDeleted();

        if("Y".equals(notice.getDelYn())){
            resultMap.put("msg", "게시글 삭제 성공");
        }else{
            resultMap.put("msg", "게시글 삭제 실패");
        }
        return resultMap;

    }
}
