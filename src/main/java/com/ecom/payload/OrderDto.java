package com.ecom.payload;

import lombok.Data;

@Data
public class OrderDto {

    private String razorpayOrderId;

    private double amount;

    private String currency;

    private String receipt;

    private String status;
}
