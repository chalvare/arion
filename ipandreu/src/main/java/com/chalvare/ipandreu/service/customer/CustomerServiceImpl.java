package com.chalvare.ipandreu.service.customer;


import com.chalvare.ipandreu.domain.Customer;
import com.chalvare.ipandreu.domain.CustomerState;
import com.chalvare.ipandreu.mapper.CustomerMapper;
import com.chalvare.ipandreu.repository.CustomerRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;

@Service
public class CustomerServiceImpl implements CustomerService{

    public static final String CUSTOMER_CB = "customerCB";
    public static final Customer FALLBACK_DATA = new Customer("CustomerDefaultId", "CustomerNameDefault", Instant.now(), CustomerState.ACTIVE,"email",0, Collections.emptyList(),Collections.emptyList(),Collections.emptyList());

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
        return customerRepository.findAll().map(CustomerMapper.INSTANCE::toCustomer);
    }

    @Override
    @Bulkhead(name = CUSTOMER_CB)
    @Retry(name = CUSTOMER_CB)
    @CircuitBreaker(name = CUSTOMER_CB, fallbackMethod = "monoFallback")
    public Mono<Customer> save(Customer c) {
        return customerRepository.save(CustomerMapper.INSTANCE.toCustomerEntity(c)).map(CustomerMapper.INSTANCE::toCustomer);
    }

    private Mono<Customer> monoFallback(HttpServerErrorException ex) {
        return Mono.just(FALLBACK_DATA);
    }
    private Flux<Customer> justFallback(HttpServerErrorException ex) {
        return Flux.just(FALLBACK_DATA);
    }
}
