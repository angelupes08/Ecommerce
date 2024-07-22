package com.ecom.service;

import com.ecom.dao.CategoryRepo;
import com.ecom.model.Category;
import com.ecom.payload.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getCategories() {

        List<Category> categories = categoryRepo.findAllNames();

        List<CategoryDto> categoryDtos = categories.stream()
                .map((element)->this.modelMapper.map(element, CategoryDto.class)).toList();

        return categoryDtos;
    }

    @Override
    public List<CategoryDto> getProductsByCategoryName(String name) {
        return null;
    }
}
