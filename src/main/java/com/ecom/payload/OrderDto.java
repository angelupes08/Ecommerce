package com.ecom.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {

    private String razorpayOrderId;

    private double amount;

    private String currency;

    private String receipt;

    private String status;
}
