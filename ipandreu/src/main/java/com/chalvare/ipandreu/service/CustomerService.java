package com.chalvare.ipandreu.service;

import com.chalvare.ipandreu.service.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<Customer> getCustomers();
    Mono<Customer>save(Customer customer);
}
