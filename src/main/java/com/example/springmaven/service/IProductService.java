package com.example.springmaven.service;

import com.example.springmaven.model.Product;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<?> deleteProduct(Integer id);

    ResponseEntity<?> editProduct(Product product, Integer id);
}
