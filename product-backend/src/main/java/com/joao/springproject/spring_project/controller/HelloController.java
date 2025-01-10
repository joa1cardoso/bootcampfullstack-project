package com.joao.springproject.spring_project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {

   
    @GetMapping("hello")
    public String getHello(){
        return "Hello Spring Boot";
    }
    
}
