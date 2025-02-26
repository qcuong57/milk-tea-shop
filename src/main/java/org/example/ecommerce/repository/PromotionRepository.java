package org.example.ecommerce.repository;

import org.example.ecommerce.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, UUID> {
    Page<Promotion> findAll(Pageable pageable);
    boolean existsByCode(String code);
}
