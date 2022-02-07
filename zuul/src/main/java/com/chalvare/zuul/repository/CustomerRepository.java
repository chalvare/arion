package com.chalvare.zuul.repository;

import com.chalvare.zuul.security.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByNameCustomer(String nombreUsuario);
    boolean existsByNameCustomer(String nombreUsuario);
    boolean existsByEmail (String email);
}
