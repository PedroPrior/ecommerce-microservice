package com.pedroprior.ecommercespring.services;

import com.pedroprior.ecommercespring.dto.CategoryDto;
import com.pedroprior.ecommercespring.entities.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(CategoryDto categoryDto);

    Category alterCategory(Long id, CategoryDto categoryDto);

    void removeCategory(Long id);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();
}
