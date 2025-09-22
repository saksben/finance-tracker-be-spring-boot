package com.finance_tracker.backend.services;

import com.finance_tracker.backend.dtos.CreateCategoryDto;
import com.finance_tracker.backend.models.Category;
import com.finance_tracker.backend.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));
    }

    public Category createCategory(CreateCategoryDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .build();
        return categoryRepository.save(category);
    }
}
