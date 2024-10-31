package com.ecom.controller;

import com.ecom.payload.ProductDto;
import com.ecom.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @Operation(summary = "Get List of Products By Name")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProductByName(@RequestParam(value = "name") String productName){

        return new ResponseEntity<>(productService.findProducts(productName), HttpStatus.OK);
    }

    @Operation(summary = "Get List of Products By Name in a Page")
    @GetMapping("/page")
    public ResponseEntity<List<ProductDto>> getProductByNamePage(
            @RequestParam(value = "name") String productName,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "2") int pageSize){

        return new ResponseEntity<>(productService.findProductsPage(productName,pageNo,pageSize), HttpStatus.OK);
    }

    @Operation(summary = "Get List of Products By Category in a Page")
    @GetMapping("/category")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryName(@RequestParam(value = "categoryName") String keyword,
                                                                      @RequestParam(value = "pageNo") int pageNo,
                                                                      @RequestParam(value = "pageSize",defaultValue = "2") int pageSize
                                                                      ){

        return new ResponseEntity<>(productService.findProductsByCategory(keyword,pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("/default")
    public String returnProduct(){

        return "No product available";
    }
}
