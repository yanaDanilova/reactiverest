package com.sprinboot.webflux.reactiverest.repositories;

import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskScheduleRepository extends JpaRepository<TaskSchedule,Integer> {
}
