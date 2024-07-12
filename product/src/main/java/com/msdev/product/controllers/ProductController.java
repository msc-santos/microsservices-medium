package com.msdev.product.controllers;

import com.msdev.product.entities.Product;
import com.msdev.product.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @GetMapping
    public ResponseEntity<Page<Product>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size){
        Page<Product> list = productServices.findAll(page, size);
        return ResponseEntity.ok(list);
    }

}
