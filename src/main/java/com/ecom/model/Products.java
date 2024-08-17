package com.ecom.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CartItems> cartItems;
}
