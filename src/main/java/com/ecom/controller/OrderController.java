package com.ecom.controller;

import com.ecom.model.Order;
import com.ecom.model.Users;
import com.ecom.payload.OrderDto;
import com.ecom.service.OrderService;
import com.ecom.service.UserService;
import com.razorpay.RazorpayException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @Operation(summary = "Create an order")
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestParam double amount, @RequestParam String currency, @RequestParam String receipt) {

        Users user = userService.getLoggedInUser();

        try {
            OrderDto order = orderService.createOrder(user, amount, currency, receipt);
            return ResponseEntity.ok(order);
        } catch (RazorpayException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update payment status")
    @PostMapping("/update-status")
    public ResponseEntity<Order> updateOrderStatus(@RequestParam String razorpayOrderId, @RequestParam String status) {
        Order order = orderService.updateOrderStatus(razorpayOrderId, status);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
