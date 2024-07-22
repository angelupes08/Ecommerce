package com.ecom.dao;

import com.ecom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    public Optional<Order> findByRazorpayOrderId(String razorpayOrderId);
}
