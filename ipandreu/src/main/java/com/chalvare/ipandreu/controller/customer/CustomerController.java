package com.chalvare.ipandreu.controller.customer;


import com.chalvare.ipandreu.domain.Customer;
import com.chalvare.ipandreu.dto.CustomerDTO;
import com.chalvare.ipandreu.mapper.CustomerMapper;
import com.chalvare.ipandreu.service.customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * CustomerController
 * Gestión básica del customer (insertar, borrar, actualizar, listar)
 */
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
        return customerService.getCustomers().map(CustomerMapper.INSTANCE::toCustomerDto);
    }

    @PostMapping("customers")
    public Mono<CustomerDTO> saveCustomer(@RequestBody Customer customer){
        return customerService.save(customer).map(CustomerMapper.INSTANCE::toCustomerDto);
    }

    @GetMapping(value = "/hello")
    public String helloWorld() {
        log.info("Inside Hello World Function");
        String response = "Hello World! " + new Date();
        log.info("Response => {}",response);
        return response;
    }

}
