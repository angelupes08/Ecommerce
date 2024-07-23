package com.ecom.service;

import com.ecom.dao.OrderItemRepo;
import com.ecom.dao.OrderRepo;
import com.ecom.model.*;
import com.ecom.payload.OrderDto;
import com.razorpay.RazorpayException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    RazorpayService razorpayService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(Users user, double amount, String currency, String receipt) throws RazorpayException {

        Cart cart = cartService.findActiveCart(user);

        com.razorpay.Order razorpayOrder = razorpayService.createOrder(amount,currency,receipt);

        Order order = new Order();

        order.setUser(user);
        order.setAmount(amount);
        order.setReceipt(receipt);
        order.setCurrency(currency);
        order.setRazorpayOrderId(razorpayOrder.get("id"));
        order.setStatus("Created");

        orderRepo.save(order);

        // Transfer items from cart to order
        List<CartItems> cartItemsList = cartService.getCartItemsFromCart(cart);
        for (CartItems cartItem : cartItemsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProducts(cartItem.getProducts());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice()); // Assuming Product entity has a getPrice method
            orderItemRepo.save(orderItem);
        }

        cartService.markCartAsInactive(cart);
        return this.modelMapper.map(order, OrderDto.class);
    }
    @Override
    public Order updateOrderStatus(String razorpayOrderId, String status) {
        Optional<Order> orderOptional = orderRepo.findByRazorpayOrderId(razorpayOrderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            return orderRepo.save(order);
        }
        return null;
    }
}
