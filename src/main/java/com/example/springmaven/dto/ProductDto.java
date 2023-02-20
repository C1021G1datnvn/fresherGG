package com.example.springmaven.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private int id;

    private String name;
    private String description;
    private String quantity;
    private long price;
    private String itemId;
}
