package com.sprinboot.webflux.reactiverest.services;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.HashMap;

@Service
public class MockTaskScheduleService implements TaskScheduleService{

    private HashMap<Integer,TaskSchedule> database;
    private int count;
    public MockTaskScheduleService() {
        this.database = new HashMap<>() {{
            put(1, new TaskSchedule(
                    1,
                    "John Smith",
                    "06.08.2023",
                    "pending project",
                    "work with project"));
        }};
        this.count = 1;
    }

    @Override
    public Flux<TaskSchedule> getAllTaskSchedule() {
        return Flux.fromIterable(database.values());
    }

    @Override
    public Mono<TaskSchedule> getTaskScheduleById(int id) {
        return Mono.just(database.get(id));
    }

    @Override
    public Mono<Boolean> create(TaskSchedule taskSchedule) {
        count++;
        taskSchedule.setId(count);
        database.put(count, taskSchedule);
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> update(TaskSchedule updatedTaskSchedule, int id) {
        TaskSchedule taskSchedule = database.get(id);
        if (taskSchedule != null) {
            taskSchedule.setEmployeeName(updatedTaskSchedule.getEmployeeName());
            taskSchedule.setTaskDate(updatedTaskSchedule.getTaskDate());
            taskSchedule.setAssignedTask(updatedTaskSchedule.getAssignedTask());
            taskSchedule.setTaskDetails(updatedTaskSchedule.getTaskDetails());
            database.put(id, taskSchedule);
            return Mono.just(true);
        }else{
            return Mono.just(false);
        }
    }

    @Override
    public Mono<Boolean> deleteTaskScheduleById(int id) {
        return Mono.just(database.remove(id)!=null);
    }
}
