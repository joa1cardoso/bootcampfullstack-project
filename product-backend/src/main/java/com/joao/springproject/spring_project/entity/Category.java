package com.joao.springproject.spring_project.entity;

import lombok.Data;

@Data
public class Category {

    int id;
    String name;

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }
    
}
