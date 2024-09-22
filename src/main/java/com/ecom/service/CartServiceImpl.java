package com.ecom.service;

import com.ecom.dao.CartItemRepo;
import com.ecom.dao.CartRepo;
import com.ecom.dao.ProductRepo;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.CartItems;
import com.ecom.model.Products;
import com.ecom.model.Users;
import com.ecom.payload.CartDto;
import com.ecom.payload.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Cart findActiveCart(Users users) {

        return cartRepo.findByUsersAndIsActive(users, 1)
                .orElseGet(() -> createActiveCart(users));
    }

    @Override
    public Cart createActiveCart(Users users) {

        Cart cart = new Cart();
        cart.setUsers(users);
        cart.setIsActive(1);

        return cartRepo.save(cart);
    }

    @Override
    public CartItems addProductstoCart(Users user, Long productId, int quantity) {


        Cart cart = findActiveCart(user);

        Products products = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("" +
                "This product does not exist"));

        CartItems cartItems = new CartItems();

        cartItems.setCart(cart);
        cartItems.setProducts(products);
        cartItems.setQuantity(quantity);
        cartItems.setPrice(cartItems.getProducts().getPrice());

        cartItems = cartItemRepo.save(cartItems);

        return cartItems;
    }

    @Override
    public List<CartDto> cartReview(Users user) {

        Cart cart = findActiveCart(user);

        List<CartItems> cartItemsList = cartItemRepo.findByCart(cart);

        List<CartDto> cartDtos = new ArrayList<>();

        //return cartItemsList.stream().map((items)->this.modelMapper.map(items,CartDto.class)).collect(Collectors.toList());

        for(CartItems c:cartItemsList){

            CartDto cartDto = new CartDto();

            ProductDto productDto = new ProductDto();

            productDto.setName(c.getProducts().getName());
            productDto.setDescription(c.getProducts().getDescription());
            productDto.setPrice(c.getProducts().getPrice());
            productDto.setStockQuantity(c.getProducts().getStockQuantity());

            cartDto.setProductDto(productDto);
            cartDto.setQuantity(c.getQuantity());
            cartDto.setPrice(c.getPrice());

            cartDtos.add(cartDto);
        }

        return cartDtos;
    }

    @Override
    public void markCartAsInactive(Cart cart) {

        cart.setIsActive(0);
        cartRepo.save(cart);

    }

    @Override
    public List<CartItems> getCartItemsFromCart(Cart cart) {

        return cartItemRepo.findByCart(cart);

    }

    @Override
    public double findCartPrice(Cart cart) {

        return cartItemRepo.findTotalPriceByCartId(cart);
    }

    @Override
    public void reduceItemsQuantity(Users user, Long productId) {

        Cart cart = findActiveCart(user);

        List<CartItems> cartItemsList = cartItemRepo.findByCart(cart);

        Products product = productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("No such product available innthe cart"));

        CartItems cartItem = cartItemRepo.findByCartAndProducts(cart,product);

        int quantity = cartItemRepo.findQuantityById(cartItem.getId());

        if(quantity>0){

            quantity--;

            cartItem.setQuantity(quantity);

            cartItemRepo.save(cartItem);
        }

        else {

            throw new RuntimeException("Quantity is already set to 0");
        }
    }

}
