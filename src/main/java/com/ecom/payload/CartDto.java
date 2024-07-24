package com.ecom.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartDto implements Serializable {

    private ProductDto productDto;

    private int quantity;

    private double price;

    public double getPrice() {
        return this.price*this.quantity;
    }


}
