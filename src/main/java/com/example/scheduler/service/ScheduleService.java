package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ScheduleService {
    // 속
    private final ScheduleRepository scheduleRepository;

    // 생
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 기
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(
                scheduleRequestDto.getTitle(),
                scheduleRequestDto.getContent(),
                scheduleRequestDto.getAuthor(),
                scheduleRequestDto.getPassword()
        );
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(saveSchedule);
    }

    public List<ScheduleResponseDto> getAllSchedules(String author) {
        List<Schedule> schedules;

        if(author != null) {
            schedules = scheduleRepository.findByAuthorOrderByUpdatedAtDesc(author);
        } else {
            schedules = scheduleRepository.findAllByOrderByUpdatedAtDesc();
        }

        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        return new ScheduleResponseDto(schedule);
    }

}
