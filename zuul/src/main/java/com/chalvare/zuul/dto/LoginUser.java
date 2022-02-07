package com.chalvare.zuul.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {

    @NotBlank
    private String nameCustomer;
    @NotBlank
    private String password;
}
