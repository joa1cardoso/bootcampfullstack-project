package com.joao.springproject.spring_project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.joao.springproject.spring_project.entity.Product;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ProductController {


    private List<Product> products = Arrays.asList(
        new Product(1, "Touca de Natação Ellus", "Descrição 01", false, false, 1, 49.90),
        new Product(2, "Short Maresia", "Descrição 02", true, true, 2, 95.50),
        new Product(3, "Regata Azul Cruzeiro Celeste", "Descrição 03", false, true, 3, 129.90)
    );
        
    
    @GetMapping("products/{id}") 
    public ResponseEntity<Product> getProduct(@PathVariable int id){
       
        // -> Forma Antiga.
       /*  if (id <= products.size()){

            return ResponseEntity.ok(products.get(id - 1));
        
        } else {
            //Lançando uma exceção para ficar mais "elegante e não mostrar uma tela 404 padrão do navegador."
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não foi encontrado");
        }   */
        
        // -> Forma atualizada com stream. (programação funcional)
        //Stream é uma forma de organizar diversos produtos, uso de stream tende a ser menor pois existem métodos não existentes dentro de uma 'List'
        //transformei produto em um stream, assim ganho todos os métodos presentes nele.
        //Resumindo, me de um produto, caso contrário me lance uma exceção 404.
        Product prod = products.stream()
                  //faz o filtro (p é um produto da stream e (p.getId() == (id) é a condição do filtro))  
                 .filter(p -> p.getId() == (id))
                 .findFirst()
                 //caso contrário a condição foi verdadeira a cima lança uma exceção com o .orElseThrow
                 .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não foi encontrado"));
        
        //retorna o produto.
         return ResponseEntity.ok(prod);


}
    
    @GetMapping("products")
    public List<Product> getProducts() {

        return products;


    }
}
