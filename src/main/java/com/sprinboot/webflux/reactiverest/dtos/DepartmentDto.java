

package com.sprinboot.webflux.reactiverest.dtos;

public class DepartmentDto {
    private int id;
    private String title;


    public DepartmentDto(int id, String title) {
        this.id = id;
        this.title = title;
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
