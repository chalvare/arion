package com.chalvare.ipandreu.repository.auction;

import com.chalvare.ipandreu.entity.AuctionEntity;
import reactor.core.publisher.Mono;


public interface CustomAuctionRepository {
    Mono<AuctionEntity> updateState (String id, String state);
    Mono<AuctionEntity> updateCustomer (String id, String customer);
    Mono<AuctionEntity> updateBet (String id, String bet);

}
