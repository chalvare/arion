package com.chalvare.ipandreu.service;

import com.chalvare.ipandreu.repository.CustomerRepository;
import com.chalvare.ipandreu.entity.CustomerEntity;
import com.chalvare.ipandreu.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    public static final String ID = "2";
    public static final String NAME = "Name";
    public static final int AGE = 25;
    public static final String BIRTHDAY = "2022-02-09T13:52:22.688132Z";
    @Mock
    private CustomerRepository customerRepository;

   @InjectMocks
   private CustomerServiceImpl customerService;

   @Test
   void getCustomers(){
       //Given
       final Flux<CustomerEntity> customer = Flux.fromIterable(Collections.singletonList(CustomerEntity.builder()
               .idCustomer(ID).birthday(BIRTHDAY).name(NAME).age(AGE).build()));

       //When
       Mockito.when(customerRepository.findAll()).thenReturn(customer);
       final Flux<Customer> customers = customerService.getCustomers();

       //Then
       StepVerifier
               .create(customers)
               .assertNext(c->assertEquals(Customer.builder().idCustomer(ID).birthday(Instant.parse(BIRTHDAY)).name(NAME).age(AGE)
                       .build(),c))
               .verifyComplete();

   }

    @Test
    void saveCustomers(){
        //Given
        CustomerEntity customerEntity = CustomerEntity.builder().birthday(BIRTHDAY).idCustomer(ID).name(NAME).age(AGE)
                .build();
        Customer customer = Customer.builder().idCustomer(ID).birthday(Instant.parse(BIRTHDAY)).name(NAME).age(AGE)
                .build();

        //When
        Mockito.when(customerRepository.save(customerEntity)).thenReturn(Mono.just(customerEntity));
        final Mono<Customer> custSaved = customerService.save(customer);

        //Then
        StepVerifier
                .create(custSaved)
                .assertNext(c->assertEquals(Customer.builder().birthday(Instant.parse(BIRTHDAY)).idCustomer(ID).name(NAME).age(AGE)
                        .build(),c))
                .verifyComplete();

    }

}