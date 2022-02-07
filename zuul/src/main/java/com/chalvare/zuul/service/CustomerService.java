package com.chalvare.zuul.service;

import com.chalvare.zuul.repository.CustomerRepository;
import com.chalvare.zuul.security.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getByCustomer(String nameCustomer){
        return customerRepository.findByNameCustomer(nameCustomer);
    }

    public Boolean existsByCustomer(String nameCustomer){
        return customerRepository.existsByNameCustomer(nameCustomer);
    }

    public Boolean existsByEmail(String email){
        return customerRepository.existsByEmail(email);
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }


}
