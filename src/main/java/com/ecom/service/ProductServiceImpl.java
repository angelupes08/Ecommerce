package com.ecom.service;

import com.ecom.dao.ProductRepo;
import com.ecom.model.Products;
import com.ecom.payload.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Cacheable(value="products",key = "#name")
    public List<ProductDto> findProducts(String name) {

        List<Products> productsList = productRepo.findByNameContaining(name);

        return productsList
                .stream().map((product)->this.modelMapper.map(product, ProductDto.class)).toList();
    }

    @Cacheable(value="products",key="#name + '-' + #pageNumber + '-' + #pageSize")
    @Override
    public List<ProductDto> findProductsPage(String name,int pageNumber,int pageSize) {

        Page<Products> productsPage = productRepo.findByNameContaining(name, PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC,"id"));
        return productsPage.
                stream().map((product)->(this.modelMapper.map(product,ProductDto.class))).collect(Collectors.toList());
    }

    @Cacheable(value="products",key = "#categoryName + '-' + #pageNumber + '-' + #pageSize")
    @Override
    public List<ProductDto> findProductsByCategory(String categoryName,int pageNumber,int pageSize) {

        Page<Products> productsList = productRepo.findByCategory_NameContaining(categoryName,
                PageRequest.of(pageNumber,pageSize,Sort.Direction.DESC,"id"));

        return productsList
                .stream().map((product)->(this.modelMapper.map(product, ProductDto.class))).collect(Collectors.toList());
    }
}
