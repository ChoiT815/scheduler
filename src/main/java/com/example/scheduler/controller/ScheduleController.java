package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 Controller
 * 클라이언트의 HTTP 요청을 받아 Service 에 전달하고 응답을 반환하는 클래스
 * 기본 URL : /schedules
 */
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // ==================== 속성 ====================

    private final ScheduleService scheduleService;

    // ==================== 생성자 ====================

    /**
     * ScheduleService 의존성 주입
     */
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // ==================== 기능 ====================

    /**
     * 일정 생성 API
     * POST /schedules
     * @param scheduleRequestDto 생성할 일정 정보 (Body)
     * @return 생성된 일정 정보 (비밀번호 제외)
     */
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.createSchedule(scheduleRequestDto);
    }

    /**
     * 전체 일정 조회 API
     * GET /schedules
     * GET /schedules?author=홍길동 (작성자명으로 필터링)
     * @param author 조회할 작성자명 (선택)
     * @return 일정 목록 (비밀번호 제외)
     */
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules(@RequestParam(required = false) String author) {
        return scheduleService.getAllSchedules(author);
    }

    /**
     * 단건 일정 조회 API
     * GET /schedules/{id}
     * @param id 조회할 일정 ID (PathVariable)
     * @return 조회된 일정 정보 (비밀번호 제외)
     */
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    /**
     * 일정 수정 API
     * PUT /schedules/{id}
     * 제목, 작성자명만 수정 가능
     * @param id 수정할 일정 ID (PathVariable)
     * @param requestDto 수정할 일정 정보 (Body) - 비밀번호 포함
     * @return 수정된 일정 정보 (비밀번호 제외)
     */
    @PutMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    /**
     * 일정 삭제 API
     * DELETE /schedules/{id}?password=1234
     * @param id 삭제할 일정 ID (PathVariable)
     * @param password 검증할 비밀번호 (QueryParam)
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestParam String password) {
       scheduleService.deleteSchedule(id, password);
       return ResponseEntity.noContent().build();
    }


}
