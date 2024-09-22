package com.ecom.dao;

import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItems,Long> {

    public List<CartItems> findByCart(Cart cart);

    public CartItems findByCartAndProducts(Cart cart, Products products);

    @Query("SELECT SUM(c.price*c.quantity) from CartItems c where c.cart=:cart group by c.cart")
    public double findTotalPriceByCartId(Cart cart);

    public int findQuantityById(Long id);
}
