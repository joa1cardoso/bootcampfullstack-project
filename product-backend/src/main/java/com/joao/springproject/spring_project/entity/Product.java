package com.joao.springproject.spring_project.entity;

import lombok.Data;

@Data
public class Product {

    private int id;
    private String name;
    private String description;
    private boolean promotion;
    private boolean newProduct;
    private int idCategory;
    private double price;


    public Product(){
        
    }


    public Product(int id, String name, double price){   
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, String description, int idCategory, boolean promotion, boolean newProduct,
            double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idCategory = idCategory;
        this.promotion = promotion;
        this.newProduct = newProduct;
        this.price = price;
    }

    
}
