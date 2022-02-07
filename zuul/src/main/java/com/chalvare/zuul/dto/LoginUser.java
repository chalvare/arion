package com.chalvare.zuul.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {

    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;
}
