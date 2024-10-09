package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.dto.request.CategoryRequest;
import com.example.alpha_cinemas.model.Category;

import java.util.Set;

public interface CategoryService {
    Category handleCreateCategory(CategoryRequest payload);
    Set<Category> handleGetCategories();
    Category handleGetCategoryByName(String name);
    void handleDeleteCategoryById(Long id);
}
