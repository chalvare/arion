package com.chalvare.zuul.service;

import com.chalvare.zuul.security.entity.Customer;
import com.chalvare.zuul.security.entity.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Clase que convierte la clase usuario en un UsuarioMain
 * UserDetailsService es propia de Spring Security
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Customer customer = customerService.getByCustomer(nombreUsuario).get();
        return UserMain.build(customer);
    }
}
