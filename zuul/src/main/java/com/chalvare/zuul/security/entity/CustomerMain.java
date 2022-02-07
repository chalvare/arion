package com.chalvare.zuul.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Encargada de generar la seguridad
 * Clase que implementa los privilegios de cada usuario
 * UserDetails es una clase propia de Spring Security
 */
public class CustomerMain implements UserDetails {

    private final String name;
    private final String customer;
    private final String email;
    private final String password;
    // Variable que nos da la autorización (no confundir con autenticación)
    // Coleccion de tipo generico que extendiende
    // de GranthedAuthority de Spring security
    private final Collection<? extends GrantedAuthority> authorities;

    //Constructor
    public CustomerMain(String name, String customer, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.customer = customer;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    //Metodo que asigna los privilegios (autorización)
    public static CustomerMain build(Customer customer){
        //Convertimos la clase Rol a la clase GrantedAuthority
        List<GrantedAuthority> authorities =
                customer.getRoles()
                        .stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getRoleName().name()))
                        .collect(Collectors.toList());
        return new CustomerMain(customer.getName(), customer.getNameCustomer(), customer.getEmail(),
                customer.getPassword(), authorities);
    }

    //@Override los que tengan esta anotación
    // significa que son metodos de UserDetails de SpringSecurity

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return customer;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
