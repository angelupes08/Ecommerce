package com.ecom.controller;

import com.ecom.model.CartItems;
import com.ecom.model.Users;
import com.ecom.payload.CartDto;
import com.ecom.service.CartService;
import com.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<String> addProducttoCart(@PathVariable Long productId,
                                                   @RequestParam(value = "quantity")int quantity){

        Users user = userService.getLoggedInUser();

        CartItems cartItems = cartService.addProductstoCart(user,productId,quantity);

        return new ResponseEntity<>("Product added to cart", HttpStatus.CREATED);
    }

    //Review cart
    @GetMapping("")
    public ResponseEntity<List<CartDto>> reviewCart(){

        Users user = userService.getLoggedInUser();

        return new ResponseEntity<>(cartService.cartReview(user),HttpStatus.OK);
    }
}
