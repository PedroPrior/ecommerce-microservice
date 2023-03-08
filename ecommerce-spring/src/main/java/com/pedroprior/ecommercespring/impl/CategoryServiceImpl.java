package com.pedroprior.ecommercespring.impl;

import com.pedroprior.ecommercespring.dto.CategoryDto;
import com.pedroprior.ecommercespring.entities.Category;
import com.pedroprior.ecommercespring.exceptions.CategoryNotFoundException;
import com.pedroprior.ecommercespring.repositories.CategoryRepository;
import com.pedroprior.ecommercespring.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Category addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        log.info("POST - New category");
        return categoryRepository.save(category);
    }

    @Override
    public Category alterCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        Category category = optionalCategory.get();

        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName());
        category.setProducts(categoryDto.getProducts());

        modelMapper.map(categoryDto, category);
        log.info("PUT - Category id: {}", category.getId().toString());
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
        log.info("DELETE - Category id: {}", id);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> getCategory = categoryRepository.findById(id);

        if (getCategory.isEmpty()) {
            throw new CategoryNotFoundException(id);
        }

        return getCategory.get();
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        log.info("GET - All categories");
        return categoryList;
    }
}
