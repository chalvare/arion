package com.chalvare.ipandreu.repository;

import com.chalvare.ipandreu.domain.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Disabled
class CustomerRepositoryTestIT {

    public static final String ID = "3";
    public static final String NAME = "name";
    public static final int AGE = 25;

    @LocalServerPort
    private String port;

    @Test
    void createCustomer(){
        Customer customer = Customer.builder().idCustomer(ID).name(NAME).age(AGE).build();
        WebTestClient.bindToServer().baseUrl("http://localhost:"+port).build()
                .post()
                .uri("/customers/customers")
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idCustomer").isNotEmpty()
                .jsonPath("$.name").isEqualTo(NAME)
                .jsonPath("$.age").isEqualTo(AGE);

    }

    @Test
    void getAllCustomers(){
        WebTestClient.bindToServer().baseUrl("http://localhost:"+port).build()
                .get()
                .uri("/customers/customers")
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Customer.class);


    }


}