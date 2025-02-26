package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.OrderItemDto;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.OrderItem;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.exception.NotFoundException;
import org.example.ecommerce.exception.BadRequestException;
import org.example.ecommerce.repository.OrderItemRepository;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Page<OrderItem> getAllOrderItems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderItemRepository.findAll(pageable);
    }

    public OrderItem getOrderItemById(UUID id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order item not found"));
    }

    public OrderItem createOrderItem(OrderItemDto dto) {
        if (dto.getQuantity() <= 0 || dto.getPrice() < 0) {
            throw new BadRequestException("Quantity must be greater than zero and price cannot be negative");
        }

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(dto.getPrice());

        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(UUID id, OrderItemDto dto) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order item not found"));

        if (dto.getQuantity() <= 0 || dto.getPrice() < 0) {
            throw new BadRequestException("Quantity must be greater than zero and price cannot be negative");
        }

        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(dto.getPrice());
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(UUID id) {
        if (!orderItemRepository.existsById(id)) {
            throw new NotFoundException("Order item not found");
        }
        orderItemRepository.deleteById(id);
    }
}