package com.chalvare.ipandreu.service;


import com.chalvare.ipandreu.repository.CustomerRepository;
import com.chalvare.ipandreu.repository.entity.CustomerEntity;
import com.chalvare.ipandreu.service.domain.Customer;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class CustomerServiceImpl implements CustomerService{

    public static final String CUSTOMER_CB = "customerCB";
    public static final Customer FALLBACK_DATA = new Customer("CustomerDefaultId", "CustomerNameDefault", LocalDate.of(1985,11,28),"email",0, Collections.emptyList(),Collections.emptyList(),Collections.emptyList());

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Bulkhead(name = CUSTOMER_CB)
    @Retry(name = CUSTOMER_CB)
    @CircuitBreaker(name = CUSTOMER_CB, fallbackMethod = "justFallback")
    public Flux<Customer> getCustomers() {
        return customerRepository.findAll().map(c -> Customer.builder().id(c.getId()).name(c.getName()).age(c.getAge())
                .build());
    }

    @Override
    @Bulkhead(name = CUSTOMER_CB)
    @Retry(name = CUSTOMER_CB)
    @CircuitBreaker(name = CUSTOMER_CB, fallbackMethod = "monoFallback")
    public Mono<Customer> save(Customer c) {
        return customerRepository.save(CustomerEntity.builder()
                        .id(c.getId()).name(c.getName()).age(c.getAge()).email(c.getEmail()).birthday(c.getBirthday())
                        .auctions(c.getAuctions())
                        .articles(c.getArticles())
                        .bets(c.getBets())
                        .build())
                .map(cs -> Customer.builder()
                        .id(cs.getId()).name(cs.getName()).age(cs.getAge()).email(cs.getEmail()).birthday(cs.getBirthday())
                        .auctions(cs.getAuctions())
                        .articles(cs.getArticles())
                        .bets(cs.getBets())
                        .build());
    }

    private Mono<Customer> monoFallback(HttpServerErrorException ex) {
        return Mono.just(FALLBACK_DATA);
    }
    private Flux<Customer> justFallback(HttpServerErrorException ex) {
        return Flux.just(FALLBACK_DATA);
    }
}
