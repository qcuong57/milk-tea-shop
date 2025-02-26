package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.entity.Review;
import org.example.ecommerce.entity.Product;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.exception.NotFoundException;
import org.example.ecommerce.repository.ReviewRepository;
import org.example.ecommerce.repository.ProductRepository;
import org.example.ecommerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Page<Review> getReviewsByProduct(UUID productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.findByProductId(productId, pageable);
    }

    public Review addReview(Review review) {
        Product product = productRepository.findById(review.getProduct().getId())
                .orElseThrow(() -> new NotFoundException("Product not found"));
        User user = userRepository.findById(review.getUser().getId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        review.setProduct(product);
        review.setUser(user);

        return reviewRepository.save(review);
    }

    public void deleteReview(UUID id) {
        if (!reviewRepository.existsById(id)) {
            throw new NotFoundException("Review not found with ID: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
