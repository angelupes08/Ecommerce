package com.ecom.service;

import com.ecom.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> getCategories();

    public List<CategoryDto> getProductsByCategoryName(String name);
}
