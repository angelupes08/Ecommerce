package com.ecom.controller;

import com.ecom.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
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

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/razorpay")
    public ResponseEntity<String> handleRazorpayWebhook(
            @RequestHeader("X-Razorpay-Signature") String signature,
            @RequestBody String payload) {

        // Verify webhook signature
        try {
            Utils.verifyWebhookSignature(payload, signature, "YOUR_SECRET");
        } catch (RazorpayException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        }

        // Process the webhook payload
        Map<String, Object> webhookData;
        try {
            webhookData = objectMapper.readValue(payload, Map.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payload");
        }
        String event = (String) webhookData.get("event");

        if ("payment.captured".equals(event)) {
            // Update order status to 'Paid'
            Map<String, Object> paymentDetails = (Map<String, Object>) webhookData.get("payload");
            String orderId = (String) paymentDetails.get("order_id");
            // Call your service to update the order status
            orderService.updateOrderStatus(orderId, "Paid");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Webhook received");
    }
}
