package com.chalvare.ipandreu.controller.globalportal;

import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.service.global.GlobalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GlobalPortalControllerTest {

    public static final String ID = "2";

    @Mock
    private GlobalService globalService;

    @InjectMocks
    private GlobalPortalController globalPortalController;

    @Test
    void shouldReturnActionDtoFromCustomer(){
        //Given
        final AuctionDTO auctionDTO = AuctionDTO.builder().idAuction(ID).build();

        //When
        Mockito.when(globalService.getAuctionsFromCustomer(ID)).thenReturn(Flux.just(auctionDTO));
        final Flux<AuctionDTO> aucDTO = globalPortalController.getAuctionsDto(ID);

        //Then
        StepVerifier
                .create(aucDTO)
                .assertNext(c-> assertEquals(ID,c.getIdAuction()))
                .verifyComplete();

    }
}