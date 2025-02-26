package org.example.ecommerce.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PromotionDto {
    private UUID id;
    private String code;
    private String name;
    private Double discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;

    public PromotionDto(UUID id, String code, String name, Double discountPercentage, LocalDateTime startDate, LocalDateTime endDate, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
}
