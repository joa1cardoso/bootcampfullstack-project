package com.joao.springproject.spring_project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joao.springproject.spring_project.entity.Product;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class ProductController {

    private List<Product> products = new ArrayList<>();

   /*  private List<Product> products = Arrays.asList(
            new Product(1, "Produto 1", "Descrição 1", 1, true, false, 29.90),
            new Product(2, "Produto 2", "Descrição 2", 2, true, true, 99.50),
            new Product(3, "Produto 3", "Descrição 3", 3, false, false, 101.10)
            
            );*/
           
    @PostMapping("products")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        product.setId(products.size() + 1);
        products.add(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {

        // -> Forma Antiga.
        /*
         * if (id <= products.size()){
         * 
         * return ResponseEntity.ok(products.get(id - 1));
         * 
         * } else {
         * //Lançando uma exceção para ficar mais
         * "elegante e não mostrar uma tela 404 padrão do navegador."
         * throw new ResponseStatusException(HttpStatus.NOT_FOUND,
         * "Produto não foi encontrado");
         * }
         */

        // -> Forma atualizada com stream. (programação funcional)
        // Stream é uma forma de organizar diversos produtos, uso de stream tende a ser
        // menor pois existem métodos não existentes dentro de uma 'List'
        // transformei produto em um stream, assim ganho todos os métodos presentes
        // nele.
        // Resumindo, me de um produto, caso contrário me lance uma exceção 404.
        Product prod = products.stream()
                // faz o filtro (p é um produto da stream e (p.getId() == (id) é a condição do
                // filtro))
                .filter(p -> p.getId() == (id))
                .findFirst()
                // caso contrário a condição foi verdadeira a cima lança uma exceção com o
                // .orElseThrow
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não foi encontrado"));

        // retorna o produto.
        return ResponseEntity.ok(prod);

    }

    @GetMapping("products")
    public List<Product> getProducts() {

        return products;

    }

}
