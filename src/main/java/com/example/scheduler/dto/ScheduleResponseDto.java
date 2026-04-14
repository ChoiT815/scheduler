package com.example.scheduler.dto;

import com.example.scheduler.entity.Schedule;

import java.time.LocalDateTime;

/**
 * 일정 응답 DTO
 * 서버가 클라이언트에게 반환하는 데이터를 담는 클래스
 * 비밀번호는 보안상 제외
 */
public class ScheduleResponseDto {
    // ==================== 속성 ====================
    private Long id; // 일정 고유 식별자
    private String title; // 일정 제목
    private String content; // 일정 내용
    private String author; // 작성자명
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일

    // ==================== 생성자 ====================

    /**
     * Schedule Entity 를 ResponseDto 로 변환하는 생성자
     * 비밀번호는 포함하지 않음
     * @param schedule 변환할 Schedule Entity
     */
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

    // ==================== Getter ====================
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


}
