package org.example.ecommerce.dto;

import lombok.*;

import java.util.UUID;

@Data
public class ReviewDto {
    private UUID id;
    private UUID userId;
    private UUID productId;
    private int rating;
    private String comment;

    public ReviewDto(UUID id, UUID userId, UUID productId, int rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
    }
}
