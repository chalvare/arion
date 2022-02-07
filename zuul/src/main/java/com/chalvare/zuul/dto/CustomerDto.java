package com.chalvare.zuul.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerDto {

    @NotBlank
    private String name;
    @NotBlank
    private String nameCustomer;
    @Email
    private String email;
    @NotBlank
    private String password;
    //Por defecto crea un usuario normal
    //Si quiero un usuario Admin debo pasar este campo roles
    private Set<String> roles = new HashSet<>();
}
