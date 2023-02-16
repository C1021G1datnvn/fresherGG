package com.example.springmaven.controller;

import com.example.springmaven.dto.ProductDto;
import com.example.springmaven.mapper.ProductMapper;
import com.example.springmaven.model.Product;
import com.example.springmaven.repository.ProductRepository;
import com.example.springmaven.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productRepository.save(productMapper.dtoToModel(productDto)), HttpStatus.CREATED);
    }

    @GetMapping("/listProduct")
    public ResponseEntity<?> listProduct() {
        return new ResponseEntity<>(productMapper.modelToDtos(productRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<?> findProductId(@PathVariable(value = "id") Integer id) {
        ProductDto productDto = productMapper.modelToDto(productRepository.findById(id).get());
        if ( productDto == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Integer id) {
       return productService.deleteProduct(id);
    }

    @PatchMapping("/editProduct/{id}")
    public ResponseEntity<?> editProduct(@PathVariable(value = "id") Integer id, @RequestBody Product product) {
        return productService.editProduct(product, id);
    }
}
