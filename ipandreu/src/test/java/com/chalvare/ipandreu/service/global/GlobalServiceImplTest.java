package com.chalvare.ipandreu.service.global;

import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.entity.AuctionEntity;
import com.chalvare.ipandreu.entity.CustomerEntity;
import com.chalvare.ipandreu.repository.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GlobalServiceImplTest {

    public static final String ID = "1";
    public static final AuctionEntity auction = AuctionEntity.builder().idAuction("123").build();
    public static final String ID_AUCTION = "123";

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private GlobalServiceImpl globalService;




    @Test
    void getCustomers(){
        //Given
        final Flux<CustomerEntity> customer = Flux.fromIterable(Collections.singletonList(CustomerEntity.builder()
                .idCustomer(ID).auctions(List.of(auction)).build()));

        //When
        Mockito.when(customerRepository.findByIdCustomer(ID)).thenReturn(customer);
        final Flux<AuctionDTO> auctionsFromCustomer = globalService.getAuctionsFromCustomer(ID);

        //Then
        StepVerifier
                .create(auctionsFromCustomer)
                .assertNext(c->assertEquals(
                        AuctionDTO.builder().idAuction(ID_AUCTION)
                        .build(),c))
                .verifyComplete();

    }

}