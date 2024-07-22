package com.ecom.service;

import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Users;
import com.ecom.payload.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    public Cart findActiveCart(Users users);

    public Cart createActiveCart(Users users);

    public CartItems addProductstoCart(Users user,Long productId, int quantity);

    public List<CartDto> cartReview(Users user);

    public void markCartAsInactive(Cart cart);

    public List<CartItems> getCartItemsFromCart(Cart cart);

}
