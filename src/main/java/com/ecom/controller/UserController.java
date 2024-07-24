package com.ecom.controller;

import com.ecom.payload.UserDto;
import com.ecom.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Registers a user in the application")
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {

        UserDto u = userService.createUser(user);

        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @Operation(summary = "View profile details")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(){

        UserDto userDto = userService.getProfile();

        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @Operation(summary = "Updates user's profile")
    @PutMapping("/profile/update")
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto userDto){

        UserDto newUserDto = userService.updateUserProfile(userDto);

        return new ResponseEntity<>(newUserDto,HttpStatus.CREATED);
    }
}
