package com.joao.springproject.spring_project.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.joao.springproject.spring_project.entity.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CategoryController {

    private List<Category> categories = Arrays.asList(new Category (1, "Touca latex altamente resistente"),
                                                    new Category(2, "Short feito de material poliester"),
                                                    new Category(3, "Regata malha fina cor azul")
                                                 );    
    


    @GetMapping("categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id){

        Category cat = categories.stream()
                        .filter(c -> c.getId() == (id))
                        .findFirst()
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não foi encontrada"));

                        return ResponseEntity.ok(cat);
    }

    @GetMapping("categories")
    public List<Category> getCategories(){
        return categories;
    }
}
                                                 
