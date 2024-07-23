package com.ecom.service;

import com.ecom.dao.CartRepo;
import com.ecom.dao.UserRepo;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.Users;
import com.ecom.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CartService cartService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CartRepo cartRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        Users user = this.modelMapper.map(userDto,Users.class);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());

        userRepo.save(user);

        cartService.createActiveCart(user);

        return userDto;
    }

    @Override
    public UserDto getProfile() {

        Users user = getLoggedInUser();

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUserProfile(UserDto userDto) {

        Users user = userRepo.findById(getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("There exists no such user"));

        user.setName(userDto.getName()!=null? userDto.getName() : user.getName());
        user.setPhoneNumber(userDto.getPhoneNumber()!=null? userDto.getPhoneNumber() : user.getPhoneNumber());

        user.setUpdatedDate(new Date());

        Users updatedUser = userRepo.save(user);

        return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public Users getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email"+email));
    }

}
