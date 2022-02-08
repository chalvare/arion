package com.chalvare.zuul.service;

import com.chalvare.zuul.security.entity.Customer;
import com.chalvare.zuul.security.entity.CustomerMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Clase que convierte la clase usuario en un UsuarioMain
 * UserDetailsService es propia de Spring Security
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerService customerService;

    @Autowired
    public UserDetailsServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String nameCustomer) throws UsernameNotFoundException {
        final Optional<Customer> byCustomer = customerService.getByCustomer(nameCustomer);
        if(byCustomer.isEmpty()) throw new UsernameNotFoundException("Username not found");
        return CustomerMain.build(byCustomer.get());
    }
}
