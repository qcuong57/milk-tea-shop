package org.example.ecommerce.dto;

import lombok.*;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private UUID categoryId;

    public ProductDto(UUID id, String name, String description, Double price, Integer stockQuantity, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }
}
