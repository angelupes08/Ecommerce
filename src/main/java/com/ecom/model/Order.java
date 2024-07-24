package com.ecom.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @Column
    private String razorpayOrderId;
    @Column
    private double amount;
    @Column
    private String currency;
    @Column
    private String receipt;
    @Column
    private String status;

}
