package com.example.demo.security.user.restaurant.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class RestaurantUserDto {
    @NotNull
    @NotEmpty
    private String encryptedPassword;

    @NotNull
    @NotEmpty
    private String email;
}
