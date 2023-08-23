package com.sprinboot.webflux.reactiverest.repositories;


import com.sprinboot.webflux.reactiverest.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
