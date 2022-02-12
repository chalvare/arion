package com.chalvare.ipandreu.controller.auction;

import com.chalvare.ipandreu.domain.Auction;
import com.chalvare.ipandreu.domain.AuctionState;
import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.service.auction.AuctionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuctionControllerTest {

    public static final String ARTICLE = "123e4567-e89b-12d3-a456-556642440000";
    public static final UUID UUID_AUCTION = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

    public static final Instant INIT_DATE = Instant.parse("2018-05-12T20:30:00Z");
    public static final Instant END_DATE = Instant.parse("2018-05-12T20:30:00Z");
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2021-12-31T15:30:00");

    @Mock
    private AuctionService auctionService;

    @InjectMocks
    private AuctionController auctionController;

    @Test
    void saveAuction(){
        //Given
        final Auction auction = Auction.builder().idAuction(UUID_AUCTION.toString()).idArticle(ARTICLE)
                .initDate(INIT_DATE)
                .endDate(END_DATE)
                .state(AuctionState.ACTIVE)
                .bets(Collections.emptyList())
                .customers(Collections.emptyList())
                .build();

        //When
        Mockito.when(
                auctionService.save(UUID.fromString(ARTICLE), LOCAL_DATE_TIME, LOCAL_DATE_TIME))
                        .thenReturn(Mono.just(auction));
        final Mono<AuctionDTO> auctionDTO = auctionController
                .saveAuction(UUID.fromString(ARTICLE), LOCAL_DATE_TIME,LOCAL_DATE_TIME);

        //Then
        StepVerifier
                .create(auctionDTO)
                .assertNext(c->{
                    assertEquals(UUID_AUCTION.toString(),c.getIdAuction());
                    assertEquals(ARTICLE,c.getIdArticle());
                    assertEquals(AuctionState.ACTIVE,c.getState());
                    assertEquals(INIT_DATE,c.getInitDate());
                    assertEquals(END_DATE,c.getEndDate());
                    assertEquals(Collections.emptyList(),c.getCustomers());
                    assertEquals(Collections.emptyList(),c.getBets());
                })
                .verifyComplete();

    }

}