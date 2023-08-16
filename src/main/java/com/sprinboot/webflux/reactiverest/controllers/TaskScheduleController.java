package com.sprinboot.webflux.reactiverest.controllers;

import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import com.sprinboot.webflux.reactiverest.exceptions.ReactiveRestNotFountException;
import com.sprinboot.webflux.reactiverest.services.TaskScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/taskSchedules")
public class TaskScheduleController {
    private TaskScheduleService taskScheduleService;

    @Autowired
    public TaskScheduleController(TaskScheduleService taskScheduleService) {
        this.taskScheduleService = taskScheduleService;
    }

    @GetMapping()
    @Operation(description = "get the entire list of task schedule")
    public Flux<TaskSchedule> getAllTaskSchedule(){
        return taskScheduleService.getAllTaskSchedule();
    }
    @GetMapping("/{id}")
    @Operation(description = "get a task schedule by ID")
    public Mono<ResponseEntity<TaskSchedule>> getTaskScheduleById(@PathVariable int id){
        throw new ReactiveRestNotFountException("TaskSchedule is not found");
/*        Mono<TaskSchedule> taskScheduleMono = taskScheduleService.getTaskScheduleById(id);
        return taskScheduleMono
                .map(taskSchedule -> ResponseEntity.ok().body(taskSchedule))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
*/
    }
    @PostMapping
    @Operation(description = "Create a new Task Schedule")
    public Mono<ResponseEntity<Void>> createTaskSchedule(@RequestBody TaskSchedule newTaskSchedule){
       return taskScheduleService.create(newTaskSchedule).map(result->{
           if(result){
               return ResponseEntity.status(HttpStatus.CREATED).build();
           }else {
               return ResponseEntity.badRequest().build();
           }
       });
    }

    @PutMapping("/{id}")
    @Operation(description = "Update the existing task schedule")
    public Mono<ResponseEntity<Void>> updateTaskScheduleById(@RequestBody TaskSchedule updatedTaskSchedule, @PathVariable int id){
        return taskScheduleService.update(updatedTaskSchedule,id).map(result->{
            if (result){
                return ResponseEntity.ok().build();
            }else
            {
                return ResponseEntity.notFound().build();
            }
        });
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete the existing task schedule by ID")
    public Mono<ResponseEntity<Void>> deleteTaskScheduleById(@PathVariable int id) {
        return taskScheduleService.deleteTaskScheduleById(id).map(result->{
            if(result){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        });
    }
}
