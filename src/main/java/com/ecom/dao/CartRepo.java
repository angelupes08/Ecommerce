package com.ecom.dao;

import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Long> {

    public Optional<Cart> findByUsersAndIsActive(Users user, int isActive);


}
