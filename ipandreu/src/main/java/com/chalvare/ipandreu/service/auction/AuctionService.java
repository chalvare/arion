package com.chalvare.ipandreu.service.auction;

import com.chalvare.ipandreu.domain.Auction;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AuctionService {
    Mono<Auction> save(UUID idArticle, LocalDateTime from, LocalDateTime to);
}
