package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.dto.request.CategoryRequest;
import com.example.alpha_cinemas.mapper.CategoryMapper;
import com.example.alpha_cinemas.model.Category;
import com.example.alpha_cinemas.repository.CategoryRepository;
import com.example.alpha_cinemas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category handleCreateCategory(CategoryRequest payload){
        return categoryRepository.save(categoryMapper.toEntity(payload));
    }

    @Override
    public Set<Category> handleGetCategories(){
        return new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category handleGetCategoryByName(String name){
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public void handleDeleteCategoryById(Long id){
         categoryRepository.deleteById(id);
    }
}
