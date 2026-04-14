package com.example.scheduler.dto;


/**
 * 일정 요청 DTO
 * 클라이언트가 서버로 보내는 데이터를 담는 클래스
 */
public class ScheduleRequestDto {
    // ==================== 속성 ====================
    private String title;    // 일정 제목
    private String content;  // 일정 내용
    private String author;   // 작성자명
    private String password; // 비밀번호 (수정/삭제 시 검증용)

    // ==================== Getter ====================
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }
}


