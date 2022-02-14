package com.chalvare.ipandreu.service.auction;

import com.chalvare.ipandreu.domain.Auction;
import com.chalvare.ipandreu.domain.AuctionState;
import com.chalvare.ipandreu.entity.AuctionEntity;
import com.chalvare.ipandreu.repository.auction.AuctionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuctionServiceImplTest {

    public static final String ARTICLE = "123e4567-e89b-12d3-a456-556642440000";
    public static final String ID_AUCTION = "123e4567-e89b-12d3-a456-556642440000";

    public static final Instant INIT_DATE = Instant.parse("2018-05-12T20:30:00Z");
    public static final Instant END_DATE = Instant.parse("2018-05-12T20:30:00Z");
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2021-12-31T15:30:00");
    public static final String ID = "ID";
    public static final String IN_PROCESS = "IN_PROCESS";

    @Mock
    private AuctionRepository auctionRepository;

    @InjectMocks
    private AuctionServiceImpl auctionService;

    @Test
    void saveEntity() {

        //Given
        AuctionEntity auctionEntity =
                AuctionEntity.builder()
                        .idAuction(ID_AUCTION)
                        .idArticle(ARTICLE)
                        .initDate("2018-05-12T20:30:00Z")
                        .endDate("2018-05-12T20:30:00Z")
                        .state("ACTIVE")
                        .customers(Collections.emptyList())
                        .bets(Collections.emptyList())
                .build();

        //When
        when(auctionRepository.save(any(AuctionEntity.class))).thenReturn(Mono.just(auctionEntity));
        final Mono<Auction> auctionMono = auctionService.save(UUID.fromString(ARTICLE),LOCAL_DATE_TIME,LOCAL_DATE_TIME);

        //Then
        StepVerifier
                .create(auctionMono)
                .assertNext(c->assertEquals(Auction.builder()
                        .idAuction(ID_AUCTION)
                        .idArticle(ARTICLE)
                        .initDate(INIT_DATE)
                        .endDate(END_DATE)
                        .idArticle(ARTICLE)
                        .state(AuctionState.ACTIVE)
                        .customers(Collections.emptyList())
                        .bets(Collections.emptyList())
                        .build(),c))
                .verifyComplete();
    }

    @Test
    void updateStateEntity() {
        //Given
        AuctionEntity auctionEntity = AuctionEntity.builder().state("IN_PROCESS").build();

        //When
        when(auctionRepository.updateState(anyString(),eq(IN_PROCESS))).thenReturn(Mono.just(auctionEntity));
        final Mono<Auction> auctionMono = auctionService.updateAuctionState(ID, IN_PROCESS);

        //Then
        StepVerifier
                .create(auctionMono)
                .assertNext(c->assertEquals(Auction.builder().state(AuctionState.IN_PROCESS).build(),c))
                .verifyComplete();
    }

    @Test
    void updateCustomersEntity() {
        //Given
        AuctionEntity auctionEntity = AuctionEntity.builder().customers(List.of("10")).build();

        //When
        when(auctionRepository.updateCustomer(anyString(),eq("10"))).thenReturn(Mono.just(auctionEntity));
        final Mono<Auction> auctionMono = auctionService.updateAuctionCustomer(ID, "10");

        //Then
        StepVerifier
                .create(auctionMono)
                .assertNext(c->assertEquals(Auction.builder().customers(List.of("10")).build(),c))
                .verifyComplete();
    }

    @Test
    void updateBetsEntity() {
        //Given
        AuctionEntity auctionEntity = AuctionEntity.builder().bets(List.of("10")).build();

        //When
        when(auctionRepository.updateBet(anyString(),eq("10"))).thenReturn(Mono.just(auctionEntity));
        final Mono<Auction> auctionMono = auctionService.updateAuctionBet(ID, "10");

        //Then
        StepVerifier
                .create(auctionMono)
                .assertNext(c->assertEquals(Auction.builder().bets(List.of("10")).build(),c))
                .verifyComplete();
    }

    @Test
    void getEntity() {

        //Given
        AuctionEntity auctionEntity =
                AuctionEntity.builder()
                        .idAuction(ID_AUCTION)
                        .idArticle(ARTICLE)
                        .initDate("2018-05-12T20:30:00Z")
                        .endDate("2018-05-12T20:30:00Z")
                        .state("ACTIVE")
                        .customers(Collections.emptyList())
                        .bets(Collections.emptyList())
                        .build();

        //When
        when(auctionRepository.findById(anyString())).thenReturn(Mono.just(auctionEntity));
        final Mono<Auction> auctionMono = auctionService.findById(ID);

        //Then
        StepVerifier
                .create(auctionMono)
                .assertNext(c->assertEquals(Auction.builder()
                        .idAuction(ID_AUCTION)
                        .idArticle(ARTICLE)
                        .initDate(INIT_DATE)
                        .endDate(END_DATE)
                        .idArticle(ARTICLE)
                        .state(AuctionState.ACTIVE)
                        .customers(Collections.emptyList())
                        .bets(Collections.emptyList())
                        .build(),c))
                .verifyComplete();
    }

    @Test
    void deleteEntity() {
        //When
        when(auctionRepository.deleteById(anyString())).thenReturn(Mono.empty());
        final Mono<Void> auctionMono = auctionService.deleteById(ID);

        //Then
        verify(auctionRepository, times(1)).deleteById(ID);
        StepVerifier
                .create(auctionMono)
                .expectNextCount(0)
                .verifyComplete();
    }


}