package com.ecom.service;

import com.ecom.model.Order;
import com.ecom.model.Users;
import com.ecom.payload.OrderDto;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

public interface OrderService {

    public OrderDto createOrder(Users user, double amount, String currency, String receipt) throws RazorpayException;

    public Order updateOrderStatus(String razorpayOrderId, String status);
}
