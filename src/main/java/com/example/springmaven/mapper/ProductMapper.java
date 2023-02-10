package com.example.springmaven.mapper;

import com.example.springmaven.dto.ProductDto;
import com.example.springmaven.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = UUID.class)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto modelToDto(Product product);

    List<ProductDto> modelToDtos(List<Product> products);

    @InheritInverseConfiguration
    Product dtoToModel(ProductDto productDto);
}
