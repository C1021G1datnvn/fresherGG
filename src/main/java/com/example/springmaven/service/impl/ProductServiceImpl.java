package com.example.springmaven.service.impl;

import com.example.springmaven.dto.ProductDto;
import com.example.springmaven.mapper.ProductMapper;
import com.example.springmaven.model.Product;
import com.example.springmaven.repository.ProductRepository;
import com.example.springmaven.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public ResponseEntity<?> deleteProduct(Integer id) {
        ProductDto productDto = productMapper.INSTANCE.modelToDto(repository.findById(id).get());
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            repository.deleteById(productDto.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> editProduct(@RequestBody Product product, Integer id) {
        ProductDto productDto = productMapper.INSTANCE.modelToDto(repository.findById(id).get());
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setQuantity(String.valueOf(product.getQuantity()));
            productDto.setPrice(product.getPrice());
            return new ResponseEntity<>(repository.save(productMapper.dtoToModel(productDto)), HttpStatus.OK);
        }
    }
}
