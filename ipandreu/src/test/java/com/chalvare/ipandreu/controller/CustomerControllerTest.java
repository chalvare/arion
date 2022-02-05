package com.chalvare.ipandreu.controller;

import com.chalvare.ipandreu.controller.dto.CustomerDTO;
import com.chalvare.ipandreu.service.CustomerService;
import com.chalvare.ipandreu.service.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    public static final String ID = "2";
    public static final String NAME = "Luis";
    public static final int AGE = 20;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    @Test
    void retrieveCustomers(){
        //Given

        final Customer customer = Customer.builder()
                .id(ID)
                .name(NAME)
                .age(AGE)
                .build();
        Mockito.when(customerService.getCustomers()).thenReturn(Flux.fromIterable(Collections.singletonList(customer)));

        //When
        final Flux<CustomerDTO> customers = customerController.getCustomers();

        //Then
        StepVerifier
                .create(customers)
                .assertNext(c->{
                    assertEquals(ID,c.getId());
                    assertEquals(NAME,c.getName());
                    assertEquals(AGE,c.getAge());
                })
                .verifyComplete();

    }

    @Test
    void saveCustomer(){
        //Given
        final Customer customer = Customer.builder().id(ID).name(NAME).age(AGE).build();

        //When
        Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(Mono.just(customer));
        final Mono<CustomerDTO> customerDTO = customerController.saveCustomer(customer);

        //Then
        StepVerifier
                .create(customerDTO)
                .assertNext(c->{
                    assertEquals(ID,c.getId());
                    assertEquals(NAME,c.getName());
                    assertEquals(AGE,c.getAge());
                })
                .verifyComplete();

    }

}