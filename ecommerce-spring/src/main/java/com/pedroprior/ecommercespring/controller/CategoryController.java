package com.pedroprior.ecommercespring.controller;


import com.pedroprior.ecommercespring.dto.CategoryDto;
import com.pedroprior.ecommercespring.entities.Category;
import com.pedroprior.ecommercespring.services.CategoryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> alterCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.alterCategory(id, categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCategory(@PathVariable Long id) {
        categoryService.removeCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category was deleted.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoryList);
    }



}
