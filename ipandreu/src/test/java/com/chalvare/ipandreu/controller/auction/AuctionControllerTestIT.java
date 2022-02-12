package com.chalvare.ipandreu.controller.auction;

import com.chalvare.ipandreu.dto.AuctionDTO;
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
class AuctionControllerTestIT {

    public static final String ID_ARTICLE = "123e4567-e89b-12d3-a456-556642440000";
    public static final String INIT_DATE = "2021-12-31T15:30:00";
    public static final String END_DATE = "2021-12-31T15:30:00";

    @LocalServerPort
    private String port;

    @Test
    void saveAuction(){
        WebTestClient.bindToServer().baseUrl("http://localhost:"+port).build()
                .post()
                .uri("/auctions/auctions/" + ID_ARTICLE + "/" +INIT_DATE+ "/" +END_DATE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(AuctionDTO.class);
    }

}