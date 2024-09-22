package com.ecom.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    private RazorpayClient razorpayClient;

    @Value("${razorpay.key_id}")

    @Value("${razorpay.key_secret}")

    public RazorpayService() throws RazorpayException {

        if (keyId == null || keySecret == null) {
            throw new IllegalArgumentException("Razorpay API key and secret must not be null");
        }

        this.razorpayClient=new RazorpayClient(keyId,keySecret);
    }

    public Order createOrder(double amount, String currency, String receipt) throws RazorpayException {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // amount in the smallest currency unit (e.g., paise for INR)
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);

        return razorpayClient.Orders.create(orderRequest);
    }
}
