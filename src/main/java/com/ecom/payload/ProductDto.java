package com.ecom.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private String description;

    private double price;

    private int stockQuantity;

}
