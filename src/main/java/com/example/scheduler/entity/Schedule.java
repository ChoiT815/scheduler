package com.example.scheduler.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 일정 Entity
 * DB의 schedules 테이블과 매핑되는 클래스
 */
@Entity
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 활성화 (작성일, 수정일 자동 관리)
public class Schedule {
    // ==================== 속성 ====================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 증가
    private Long id;
    private String title;    // 일정 제목
    private String content;  // 일정 내용
    private String password; // 비밀번호 (응답 시 제외)

    @CreatedDate // 최초 생성 시 자동으로 현재 시간 저장
    private LocalDateTime createdAt; // 작성일

    @LastModifiedDate // 수정 시 자동으로 현재 시간 업데이트
    private LocalDateTime updatedAt; // 수정일
    private String author;           // 작성자명

    // ==================== 생성자 ====================

    /**
     * JPA 가 내부적으로 사용하는 기본 생성자
     * 외부에서 직접 호출 불가
     */

    protected Schedule() { // JPA가 쓰는 생성자
    }

    /**
     * 일정 생성 시 사용하는 생성자
     * @param title 일정 제목
     * @param content 일정 내용
     * @param author 작성자명
     * @param password 비밀번호
     */

    public Schedule(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }


    // ==================== 기능 ====================

    /**
     * 일정 수정 메서드
     * 제목과 작성자명만 수정 가능
     * @param title 수정할 제목
     * @param author 수정할 작성자명
     */

    public void update(String title, String author) {
        this.title = title;
        this.author = author;
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

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getAuthor() {
        return author;
    }

}
