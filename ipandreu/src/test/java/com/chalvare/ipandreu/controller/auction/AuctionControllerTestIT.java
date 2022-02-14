package com.chalvare.ipandreu.controller.auction;

import com.chalvare.ipandreu.domain.AuctionState;
import com.chalvare.ipandreu.dto.AuctionDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuctionControllerTestIT {

    public static final String ID_ARTICLE = "123e4567-e89b-12d3-a456-556642440000";
    public static final String INIT_DATE = "2021-12-31T15:30:00";
    public static final String END_DATE = "2021-12-31T15:30:00";

    @LocalServerPort
    private String port;

    @Test
    @Order(1)
    void saveAuction(){
        final FluxExchangeResult<AuctionDTO> auctionDTOFluxExchangeResult = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build()
                .post()
                .uri("/auctions/auctions/" + ID_ARTICLE + "/" + INIT_DATE + "/" + END_DATE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(AuctionDTO.class);

        final AuctionDTO auctionDTO = auctionDTOFluxExchangeResult.getResponseBody().blockFirst();

        assert auctionDTO != null;
        WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build()
                .get()
                .uri("/auctions/auctions/" + auctionDTO.getId() )
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AuctionDTO.class)
                .isEqualTo(auctionDTO);

        auctionDTO.setState(AuctionState.IN_PROCESS);
        WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build()
                .put()
                .uri("/auctions/auctions/" + auctionDTO.getId()+"/state" )
                .body(Mono.just("IN_PROCESS"), String.class)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AuctionDTO.class)
                .isEqualTo(auctionDTO);

        auctionDTO.getCustomers().add("10");
        auctionDTO.setCustomers(auctionDTO.getCustomers());
        WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build()
                .put()
                .uri("/auctions/auctions/" + auctionDTO.getId()+"/customer" )
                .body(Mono.just("10"), String.class)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AuctionDTO.class)
                .isEqualTo(auctionDTO);

        auctionDTO.getBets().add("10");
        auctionDTO.setBets(auctionDTO.getBets());
        WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build()
                .put()
                .uri("/auctions/auctions/" + auctionDTO.getId()+"/bet" )
                .body(Mono.just("10"), String.class)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AuctionDTO.class)
                .isEqualTo(auctionDTO);

        WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build()
                .delete()
                .uri("/auctions/auctions/"+ auctionDTO.getId())
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk();
    }


}