package com.ecom.payload;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
public class UserDto {

    private String email;

    private String password;

    private String name;

    private String phoneNumber;
}
