package com.chalvare.ipandreu.service.global;

import com.chalvare.ipandreu.dto.AuctionDTO;
import reactor.core.publisher.Flux;

public interface GlobalService {
    Flux<AuctionDTO>  getAuctionsFromCustomer(String customerId);
}
