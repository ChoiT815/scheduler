package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByAuthorOrderByUpdatedAtDesc(String author);
    List<Schedule> findAllByOrderByUpdatedAtDesc();
}
