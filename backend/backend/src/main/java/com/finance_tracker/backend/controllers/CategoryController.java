package com.finance_tracker.backend.controllers;

import com.finance_tracker.backend.dtos.CreateCategoryDto;
import com.finance_tracker.backend.models.Category;
import com.finance_tracker.backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody CreateCategoryDto dto) {
        return categoryService.createCategory(dto);
    }
}
