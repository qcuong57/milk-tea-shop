package org.example.ecommerce.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
public class OrderDto {
    private UUID id;
    private UUID userId;
    private LocalDateTime orderDate;
    private String status;
    private Double totalPrice;
    private List<OrderItemDto> orderItems;

    public OrderDto(UUID id, UUID userId, LocalDateTime orderDate, String status, Double totalPrice, List<OrderItemDto> orderItems) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }
}