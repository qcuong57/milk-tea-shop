package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.CategoryDto;
import org.example.ecommerce.dto.PaginationDTO;
import org.example.ecommerce.entity.Category;
import org.example.ecommerce.exception.NotFoundException;
import org.example.ecommerce.repository.CategoryRepository;
import org.example.ecommerce.respone.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final PaginationService paginationService;

    public CategoryService(CategoryRepository categoryRepository, PaginationService paginationService) {
        this.categoryRepository = categoryRepository;
        this.paginationService = paginationService;
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDto(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    public PaginationResponse<List<CategoryDto>> getCategories(PaginationDTO paginationDTO) {
        Pageable pageable = paginationService.getPageable(paginationDTO);
        Page<Category> categories = categoryRepository.findAll(pageable);

        List<CategoryDto> categoryDTOs = categories.getContent().stream()
                .map(category -> new CategoryDto(category.getId(), category.getName()))
                .collect(Collectors.toList());

        return new PaginationResponse<>(
                true,
                "Get categories successfully",
                categoryDTOs,
                paginationDTO.getPage(),
                paginationDTO.getSize(),
                categories.getTotalPages(),
                categories.getTotalElements()
        );
    }

    public CategoryDto getCategoryById(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        return new CategoryDto(category.getId(), category.getName());
    }

    public CategoryDto createCategory(CategoryDto categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category = categoryRepository.save(category);
        return new CategoryDto(category.getId(), category.getName());
    }

    public CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        category.setName(categoryDTO.getName());
        category = categoryRepository.save(category);
        return new CategoryDto(category.getId(), category.getName());
    }

    public void deleteCategory(UUID categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category not found");
        }
        categoryRepository.deleteById(categoryId);
    }
}
