package com.sprinboot.webflux.reactiverest.controllers;

import com.sprinboot.webflux.reactiverest.dtos.TaskScheduleDto;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import com.sprinboot.webflux.reactiverest.services.ITaskScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/taskSchedules")
public class TaskScheduleController {
    private final ITaskScheduleService taskScheduleService;

    @Autowired
    public TaskScheduleController(ITaskScheduleService taskScheduleService) {
        this.taskScheduleService = taskScheduleService;
    }

    @GetMapping("/index")
    @Operation(description = "Index of this controller")
    public Mono<String> index(){
        int i = 0;
        int j = 1;
        int k = j/i;
        return Mono.just("Hello World!");
    }

    @GetMapping()
    @Operation(description = "get the entire list of task schedule")
    public Flux<TaskScheduleDto> getAllTaskSchedule(){
        return taskScheduleService.getAllTaskSchedule();
    }
    @GetMapping("/{id}")
    @Operation(description = "get a task schedule by ID")
    public Mono<ResponseEntity<TaskScheduleDto>> getTaskScheduleById(@PathVariable int id){
       Mono<TaskScheduleDto> taskScheduleDtoMono = taskScheduleService.getTaskScheduleById(id);
        return taskScheduleDtoMono
                .map(taskScheduleDto -> ResponseEntity.ok().body(taskScheduleDto))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }
    @PostMapping
    @Operation(description = "Create a new Task Schedule")
    public Mono<ResponseEntity<TaskScheduleDto>> createTaskSchedule(@RequestBody TaskScheduleDto newTaskScheduleDto){
       return taskScheduleService.create(newTaskScheduleDto).map(taskSchedule -> ResponseEntity.ok().body(taskSchedule))
               .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{id}")
    @Operation(description = "Update the existing task schedule")
    public Mono<ResponseEntity<Void>> updateTaskScheduleById(@RequestBody TaskScheduleDto updatedTaskScheduleDto, @PathVariable int id){
        return taskScheduleService.update(updatedTaskScheduleDto,id).map(result->{
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
