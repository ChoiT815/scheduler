package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 일정 Repository
 * DB 접근을 담당하는 인터페이스
 * JpaRepository 를 상속받아 기본 CRUD 기능 제공
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * 작성자명으로 조회 + 수정일 기준 내림차순 정렬
     * @param author 조회할 작성자명
     * @return 해당 작성자의 일정 목록
     */
    List<Schedule> findByAuthorOrderByUpdatedAtDesc(String author);

    /**
     * 전체 조회 + 수정일 기준 내림차순 정렬
     * @return 전체 일정 목록
     */
    List<Schedule> findAllByOrderByUpdatedAtDesc();
}
