package com.ecom.dao;

import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItems,Long> {

    public List<CartItems> findByCart(Cart cart);
}
