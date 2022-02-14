package com.chalvare.ipandreu.service.auction;

import com.chalvare.ipandreu.domain.Auction;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.UUID;

public interface AuctionService {
    Mono<Auction> save(UUID idArticle, LocalDateTime from, LocalDateTime to);
    Mono<Auction> updateAuctionState(String id, String state);
    Mono<Auction> updateAuctionCustomer(String id, String customer);
    Mono<Auction> updateAuctionBet(String id, String bet);
    Mono<Auction> findById(String idAuction);
    Mono<Void> deleteById(String idAuction);
}
