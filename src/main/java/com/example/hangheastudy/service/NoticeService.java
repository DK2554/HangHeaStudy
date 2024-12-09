package com.example.hangheastudy.service;

import com.example.hangheastudy.dto.NoticeDto;
import com.example.hangheastudy.dto.SearchNoticeRequest;
import com.example.hangheastudy.entity.Notice;
import com.example.hangheastudy.repository.NoticeRepository;
import com.example.hangheastudy.util.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeDto> searchNotices(SearchNoticeRequest request) {
        Specification<Notice> spec = Specification.where(null);
        System.out.println(request.toString());
        // 제목 조건 추가
        if (request.getNoticeTitle() != null && !request.getNoticeTitle().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("noticeTitle"), "%" + request.getNoticeTitle() + "%"));
        }

        // 작성자 조건 추가 (equal: 정확히 일치하는 검색)
        if (request.getRegId() != null && !request.getRegId().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("regId"), request.getRegId()));
        }
        // 작성 날짜 조건 추가
        if (request.getRegDt() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("regDt").as(LocalDate.class), request.getRegDt()));
        }

        // 엔티티 결과를 DTO로 변환하여 반환
        Sort sort = Sort.by(Sort.Direction.DESC, "regDt");

        // 엔티티 결과를 DTO로 변환하여 반환
        return noticeRepository.findAll(spec, sort).stream()
                .map(notice -> NoticeDto.builder()
                        .userId(notice.getUserId())
                        .noticeId(notice.getNoticeId())
                        .noticeTitle(notice.getNoticeTitle())
                        .noticeContent(notice.getNoticeContent())
                        .regDt(notice.getRegDt())
                        .regId(notice.getRegId())
                        .modDt(notice.getModDt())
                        .modId(notice.getModId())
                        .build())
                .toList();

    }

    public NoticeDto getNotice(Long id) {
        String currentUser = UserContext.getUser(); // 현재 사용
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notice not found"));
        return NoticeDto
                .builder()
                .noticeId(notice.noticeId)
                .userId(notice.getUserId())
                .noticeContent(notice.getNoticeContent())
                .noticeTitle(notice.getNoticeTitle())
                .regDt(notice.getRegDt())
                .regId(currentUser)
                .build();

    }

    @Transactional
    public NoticeDto updateNotice(Long id, NoticeDto noticeDto) {
        String currentUser = UserContext.getUser(); // 현재 사용
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
                .regId(currentUser)
                .modId(currentUser)
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

    public NoticeDto postNotice(NoticeDto noticeDto) {
        String currentUser = UserContext.getUser(); // 현재 사용
        Notice notice = Notice.builder()
                .noticeTitle(noticeDto.getNoticeTitle())
                .noticeContent(noticeDto.getNoticeContent())
                .userId(noticeDto.getUserId())
                .delYn("N")
                .regId(currentUser)
                .modId(currentUser)
                .build();
        System.out.println(currentUser);

        Notice savedNotice = noticeRepository.save(notice);

        return NoticeDto.builder()
                .noticeId(savedNotice.getNoticeId())
                .noticeTitle(savedNotice.getNoticeTitle())
                .noticeContent(savedNotice.getNoticeContent())
                .userId(savedNotice.getUserId())
                .regId(savedNotice.getRegId())
                .regDt(savedNotice.getRegDt())
                .build();

    }
}
