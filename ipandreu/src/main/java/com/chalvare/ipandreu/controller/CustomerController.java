package com.chalvare.ipandreu.controller;


import com.chalvare.ipandreu.controller.dto.CustomerDTO;
import com.chalvare.ipandreu.service.CustomerService;
import com.chalvare.ipandreu.service.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customers")
    public Flux<CustomerDTO> getCustomers(){
        return customerService.getCustomers().map(c->CustomerDTO.builder().id(c.getId()).name(c.getName()).age(c.getAge())
                .build());
    }

    @PostMapping("customers")
    public Mono<CustomerDTO> saveCustomer(@RequestBody Customer customer){
        return customerService.save(customer).map(c->
                CustomerDTO.builder()
                        .id(c.getId()).name(c.getName()).age(c.getAge()).email(c.getEmail()).birthday(c.getBirthday())
                        .auctions(c.getAuctions())
                        .articles(c.getArticles())
                        .bets(c.getBets())
                .build());
    }

    @GetMapping(value = "/hello")
    public String helloWorld() {
        log.info("Inside Hello World Function");
        String response = "Hello World! " + new Date();
        log.info("Response => {}",response);
        return response;
    }

}
