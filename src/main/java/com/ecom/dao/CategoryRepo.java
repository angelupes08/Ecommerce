package com.ecom.dao;

import com.ecom.model.Category;
import com.ecom.payload.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    @Query("SELECT new com.ecom.payload.CategoryDto(c.name) from Category c")
    public List<Category> findAllNames();

}
