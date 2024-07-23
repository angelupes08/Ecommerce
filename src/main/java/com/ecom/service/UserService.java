package com.ecom.service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.Users;
import com.ecom.payload.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto getProfile();

    public UserDto updateUserProfile(UserDto userDto);

    public Users getLoggedInUser() throws ResourceNotFoundException;


}
