package com.chalvare.zuul.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private String nameCustomer;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String nameCustomer, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nameCustomer = nameCustomer;
        this.authorities = authorities;
    }
}
