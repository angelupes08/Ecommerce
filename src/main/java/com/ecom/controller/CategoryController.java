package com.ecom.controller;

import com.ecom.payload.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Operation(summary = "Get List of Products By Category")
    @GetMapping("")
    public ResponseEntity<CategoryDto> getProductsByCategoryName(@RequestParam(value = "categoryName") String keyword,
                                                     @RequestParam(value = "pageNo") int pageNo,
                                                     @RequestParam(value = "pageSize") int pageSize){

        return null;
    }
}
