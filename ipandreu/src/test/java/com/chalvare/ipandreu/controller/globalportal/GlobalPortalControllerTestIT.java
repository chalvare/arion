package com.chalvare.ipandreu.controller.globalportal;

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

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Disabled
class GlobalPortalControllerTestIT {

    private static final String ID_AUCTIONS = "1";
    @LocalServerPort
    private String port;

    @Test
    void requestCustomers(){
        WebTestClient.bindToServer().baseUrl("http://localhost:"+port).build()
                .get()
                .uri("/global/auctions/"+ ID_AUCTIONS)
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Customer.class);
    }

}