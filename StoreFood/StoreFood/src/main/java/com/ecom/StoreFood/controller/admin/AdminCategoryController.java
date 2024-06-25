package com.ecom.StoreFood.controller.admin;

import com.ecom.StoreFood.dto.CategoryDto;
import com.ecom.StoreFood.entity.Category;
import com.ecom.StoreFood.services.adminCategory.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class AdminCategoryController {
    private final CategoryService categoryService;
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory (@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createcategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
