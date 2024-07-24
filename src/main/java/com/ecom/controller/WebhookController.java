package com.ecom.controller;

import com.ecom.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import io.swagger.v3.oas.annotations.Operation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("webhook")
public class WebhookController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/razorpay")
    @Operation(summary = "Handles webhook through Razorpay")
    public  ResponseEntity<Void> handleRazorpayWebhook(@RequestBody String payload) {
        // Process the webhook payload to update the order status
        // Example: Verify the signature, extract order ID and status, update the order status

        // Assuming you have a method to process the webhook payload
        processWebhookPayload(payload);

        return ResponseEntity.ok().build();
    }

    private void processWebhookPayload(String payload) {
        // Parse and validate the payload
        // Extract razorpay_order_id and payment status
        // Update order status based on payment status

        // Example code (not complete, needs signature verification and proper parsing):
        JSONObject webhookPayload = new JSONObject(payload);
        String razorpayOrderId = webhookPayload.getString("order_id");
        String status = webhookPayload.getString("status");

        orderService.updateOrderStatus(razorpayOrderId, status);
    }
}
