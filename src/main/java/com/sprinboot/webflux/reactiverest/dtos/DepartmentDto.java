

package com.sprinboot.webflux.reactiverest.dtos;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFilter("DepartmentDtoFilter")
public class DepartmentDto {

    private int id;
    @JsonProperty("department_name")
    private String title;

    @JsonProperty("count_of_employees")
    private int employeesCount;


    public DepartmentDto(int id, String title,int employeesCount) {
        this.id = id;
        this.title = title;
        this.employeesCount = employeesCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
