package com.sprinboot.webflux.reactiverest.controllers;

import com.sprinboot.webflux.reactiverest.dtos.EmployeeDto;
import com.sprinboot.webflux.reactiverest.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

   private final EmployeeService employeeService;

   @Autowired
   public EmployeeController(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }

   @GetMapping()
   @Operation(description = "get the entire list of employees")
   public Flux<EmployeeDto> getAllEmployees(){
      return employeeService.getAllEmployee();
   }

   @GetMapping("/{id}")
   @Operation(description = "get a employee by ID")
   public Mono<ResponseEntity<EmployeeDto>> getEmployeeById(@PathVariable int id){
      Mono<EmployeeDto> employeeDtoMono = employeeService.getEmployeeById(id);
      return employeeDtoMono
              .map(employeeDto -> ResponseEntity.ok().body(employeeDto))
              .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

   }
   @PostMapping
   @Operation(description = "Create a new Employee")
   public Mono<ResponseEntity<Void>> createEmployee(@Valid @RequestBody EmployeeDto newEmployeeDto){
      return employeeService.create(newEmployeeDto).map(result->{
         if (result){
            return ResponseEntity.status(HttpStatus.CREATED).build();
         }else
         {
            return ResponseEntity.badRequest().build();
         }
      });
   }

   @PutMapping("/{id}")
   @Operation(description = "Update the existing employee by id")
   public Mono<ResponseEntity<Void>> updateEmployeeById(@Valid @RequestBody EmployeeDto updatedEmployeeDto, @PathVariable int id){
      return employeeService.update(updatedEmployeeDto,id).map(result->{
         if (result){
            return ResponseEntity.ok().build();
         }else
         {
            return ResponseEntity.notFound().build();
         }
      });
   }

   @DeleteMapping("/{id}")
   @Operation(description = "Delete the existing employee by ID")
   public Mono<ResponseEntity<Void>> deleteEmployeeId(@PathVariable int id) {
      return employeeService.deleteEmployeeById(id).map(result->{
         if(result){
            return ResponseEntity.ok().build();
         }else{
            return ResponseEntity.notFound().build();
         }
      });
   }

}

