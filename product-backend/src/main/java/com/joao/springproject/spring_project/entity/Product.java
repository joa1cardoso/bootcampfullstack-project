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

    public Product(int id, String name, double price){   
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, String description, boolean promotion, boolean newProduct, int idCategory,
            double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.promotion = promotion;
        this.newProduct = newProduct;
        this.idCategory = idCategory;
        this.price = price;
    }

    
}
