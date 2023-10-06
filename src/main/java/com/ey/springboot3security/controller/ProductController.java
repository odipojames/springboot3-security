package com.ey.springboot3security.controller;

import com.ey.springboot3security.service.ProductService;
import com.ey.springboot3security.entity.Product;
import com.ey.springboot3security.entity.ProductDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.*;
import java.util.*;

@RestController
public class ProductController {

    @Autowired private ProductService service;
    
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/products")
    public List<Product> findAll() {
    
        return service.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
    
        try{
            Product product = service.get(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/products")
    public Product add(@RequestBody @Valid Product product) {
    
        return service.save(product);
    }
    
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PutMapping("/products/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,@PathVariable Integer id) {
    
        try{
            Product productExists = service.get(id);
            productExists.setName(product.getName());
            productExists.setPrice(product.getPrice());
            service.save(productExists);
            return new ResponseEntity<>(productExists,HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  
    }
@PreAuthorize("hasAuthority('ROLE_ADMIN')")    
@PatchMapping("/products/{id}")//manual though
    public ResponseEntity<Product> updateProducts(@RequestBody Product product,@PathVariable Integer id) {
    
        try{
            Product productExists = service.get(id);
           if(product.getName() != null){
             productExists.setName(product.getName());
           }
            if(product.getPrice() != 0.0f){
                productExists.setPrice(product.getPrice());
            }
            service.save(productExists);
            return new ResponseEntity<>(productExists,HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  
    }

@PatchMapping("productsx/{id}")//to-do
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productUpdateDTO) {
        Product updatedProduct = service.patchProduct(id, productUpdateDTO);
        return ResponseEntity.ok(updatedProduct);
    }  

@PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        service.delete(id);
        return new ResponseEntity<>("Product Deleted",HttpStatus.OK);

    }

   

}