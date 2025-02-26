package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.entity.Promotion;
import org.example.ecommerce.exception.NotFoundException;
import org.example.ecommerce.repository.PromotionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Page<Promotion> getAllPromotions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return promotionRepository.findAll(pageable);
    }

    public Promotion getPromotionById(UUID id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Promotion not found"));
    }

    public Promotion createPromotion(Promotion promotion) {
        if (promotionRepository.existsByCode(promotion.getCode())) {
            throw new RuntimeException("Promotion code already exists: " + promotion.getCode());
        }
        return promotionRepository.save(promotion);
    }

    public Promotion updatePromotion(UUID id, Promotion updatedPromotion) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Promotion not found with ID: " + id));

        promotion.setCode(updatedPromotion.getCode());
        promotion.setName(updatedPromotion.getName());
        promotion.setDiscountPercentage(updatedPromotion.getDiscountPercentage());
        promotion.setStartDate(updatedPromotion.getStartDate());
        promotion.setEndDate(updatedPromotion.getEndDate());
        promotion.setIsActive(updatedPromotion.getIsActive());

        return promotionRepository.save(promotion);
    }

    public void deletePromotion(UUID id) {
        if (!promotionRepository.existsById(id)) {
            throw new NotFoundException("Promotion not found with ID: " + id);
        }
        promotionRepository.deleteById(id);
    }
}