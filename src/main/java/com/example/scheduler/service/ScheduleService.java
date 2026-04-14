package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 일정 Service
 * 비즈니스 로직을 담당하는 클래스
 * 비밀번호 검증, 데이터 변환 등 핵심 로직 처리
 */
@Service
public class ScheduleService {
    // ==================== 속성 ====================

    private final ScheduleRepository scheduleRepository;

    // ==================== 생성자 ====================

    /**
     * ScheduleRepository 의존성 주입
     */
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // ==================== 기능 ====================

    /**
     * 일정 생성
     * @param scheduleRequestDto 생성할 일정 정보
     * @return 생성된 일정 정보 (비밀번호 제외)
     */
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        // RequestDto 를 Entity 로 변환
        Schedule schedule = new Schedule(
                scheduleRequestDto.getTitle(),
                scheduleRequestDto.getContent(),
                scheduleRequestDto.getAuthor(),
                scheduleRequestDto.getPassword()
        );

        // DB 에 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity 를 ResponseDto 로 변환 후 반환
        return new ScheduleResponseDto(saveSchedule);
    }

    /**
     * 전체 일정 조회
     * 작성자명이 있으면 해당 작성자 일정만, 없으면 전체 조회
     * 수정일 기준 내림차순 정렬
     * @param author 조회할 작성자명 (선택)
     * @return 일정 목록 (비밀번호 제외)
     */
    public List<ScheduleResponseDto> getAllSchedules(String author) {
        List<Schedule> schedules;

        if(author != null) {
            // 작성자명으로 필터링
            schedules = scheduleRepository.findByAuthorOrderByUpdatedAtDesc(author);
        } else {
            // 전체 조회
            schedules = scheduleRepository.findAllByOrderByUpdatedAtDesc();
        }

        // Entity 목록을 ResponseDto 목록으로 변환 후 반환
        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    /**
     * 단건 일정 조회
     * @param id 조회할 일정 ID
     * @return 조회된 일정 정보 (비밀번호 제외)
     */
    public ScheduleResponseDto getSchedule(Long id) {
        // ID 로 일정 조회, 없으면 예외 발생
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        return new ScheduleResponseDto(schedule);
    }


    /**
     * 일정 수정
     * 제목, 작성자명만 수정 가능
     * 비밀번호 검증 필수
     * @param id 수정할 일정 ID
     * @param requestDto 수정할 일정 정보
     * @return 수정된 일정 정보 (비밀번호 제외)
     */
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // ID 로 일정 조회, 없으면 예외 발생
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        // 비밀번호 검증
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 제목, 작성자명 수정
        schedule.update(requestDto.getTitle(), requestDto.getAuthor());
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 일정 삭제
     * 비밀번호 검증 필수
     * @param id 삭제할 일정 ID
     * @param password 검증할 비밀번호
     */
    @Transactional
    public void deleteSchedule(Long id, String password) {
        // ID 로 일정 조회, 없으면 예외 발생
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        // 비밀번호 검증
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 일정 삭제
        scheduleRepository.delete(schedule);
    }

}
