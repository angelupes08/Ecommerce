package com.ecom.controller;

import com.ecom.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productService;

    @Test
    void getProductByName() {

       // when(productService.findProducts("abc")).thenReturn();
    }

    @Test
    void getProductByNamePage() {
    }

    @Test
    void getProductsByCategoryName() {
    }
}