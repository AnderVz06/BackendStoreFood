package com.ecom.StoreFood.services.adminCategory;

import com.ecom.StoreFood.dto.CategoryDto;
import com.ecom.StoreFood.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createcategory (CategoryDto categoryDto);
    List<Category> getAllCategories();
}
