package com.ecom.service;

import com.ecom.payload.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public List<ProductDto> findProducts(String name);

    public List<ProductDto> findProductsPage(String name,int pageNumber,int pageSize);

    public List<ProductDto> findProductsByCategory(String categoryName,int pageNumber,int pageSize);
}
