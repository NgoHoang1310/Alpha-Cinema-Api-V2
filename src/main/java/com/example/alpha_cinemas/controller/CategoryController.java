package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.dto.request.CategoryRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.model.Category;
import com.example.alpha_cinemas.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories(){
        Set<Category> response = categoryService.handleGetCategories();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !",response, null));
    }

    @GetMapping(value = "/categories", params = "name")
    public ResponseEntity<?> getCategoryByName(@RequestParam("name") String name){
        Category response = categoryService.handleGetCategoryByName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !",response, null));
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest payload){
        Category response = categoryService.handleCreateCategory(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !",response, null));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> createCategory(@PathVariable Long id){
       categoryService.handleDeleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Category is deleted !",null, null));
    }
}
