package com.ecom.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products products;

    @Column
    private int quantity;

    @Column
    private double price;
}
