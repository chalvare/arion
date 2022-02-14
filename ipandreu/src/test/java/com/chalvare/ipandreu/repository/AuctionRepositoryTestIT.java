package com.chalvare.ipandreu.repository;

import com.chalvare.ipandreu.domain.Auction;
import com.chalvare.ipandreu.domain.AuctionState;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Disabled
class AuctionRepositoryTestIT {

    public static final String ARTICLE = "123e4567-e89b-12d3-a456-556642440000";
    public static final String ID_AUCTION = "123e4567-e89b-12d3-a456-556642440000";

    @LocalServerPort
    private String port;

    @Test
    void createAuction(){
        WebTestClient.bindToServer().baseUrl("http://localhost:"+port).build()
                .post()
                .uri("/auctions/auctions/"+ARTICLE+"/2018-05-12T20:30:00/2018-05-12T20:30:00")
                .header(ACCEPT,APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idAuction").isNotEmpty()
                .jsonPath("$.idArticle").isEqualTo(ARTICLE)
                .jsonPath("$.initDate").isEqualTo("2018-05-12T20:30:00Z")
                .jsonPath("$.endDate").isEqualTo("2018-05-12T20:30:00Z")
                .jsonPath("$.state").isEqualTo("ACTIVE")
                .jsonPath("$.bets").isArray()
                .jsonPath("$.customers").isArray();

    }



}