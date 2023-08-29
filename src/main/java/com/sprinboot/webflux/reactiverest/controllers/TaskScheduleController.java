package com.sprinboot.webflux.reactiverest.controllers;

import com.sprinboot.webflux.reactiverest.dtos.TaskScheduleDto;
import com.sprinboot.webflux.reactiverest.services.ITaskScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;

@RestController
@RequestMapping("/api/taskSchedules")
public class TaskScheduleController {

    private final MessageSource messageSource;
    private final ITaskScheduleService taskScheduleService;

    @Autowired
    public TaskScheduleController(MessageSource messageSource, ITaskScheduleService taskScheduleService) {
        this.messageSource = messageSource;
        this.taskScheduleService = taskScheduleService;
    }

    @GetMapping("/greeting-internationalized")
    @Operation(description = "greeting in different languages")
    public Mono<String> index(){
        Locale locale= LocaleContextHolder.getLocale();
        return Mono.just(messageSource.getMessage("greeting.message",null,locale));
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
    @PostMapping()
    @Operation(description = "Create a new Task Schedule")
    public Mono<ResponseEntity<TaskScheduleDto>> createTaskSchedule(@Valid @RequestBody TaskScheduleDto newTaskScheduleDto){
        return taskScheduleService.create(newTaskScheduleDto).map(taskSchedule -> ResponseEntity.ok().body(taskSchedule))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{id}")
    @Operation(description = "Update the existing task schedule")
    public Mono<ResponseEntity<Void>> updateTaskScheduleById(@Valid @RequestBody TaskScheduleDto updatedTaskScheduleDto, @PathVariable int id){
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
