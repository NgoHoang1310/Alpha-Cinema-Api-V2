package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryByName(String name);
    Category findCategoryById(Long id);
}
