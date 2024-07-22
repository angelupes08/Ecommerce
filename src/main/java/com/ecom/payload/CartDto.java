package com.ecom.payload;

import lombok.Data;

@Data
public class CartDto {

    private ProductDto productDto;

    private int quantity;
}
