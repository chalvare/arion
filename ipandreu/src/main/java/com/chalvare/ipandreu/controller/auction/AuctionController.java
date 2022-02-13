package com.chalvare.ipandreu.controller.auction;


import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.mapper.AuctionMapper;
import com.chalvare.ipandreu.service.auction.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("auctions/{idArticle}/{from}/{to}")
    public Mono<AuctionDTO> saveAuction(@PathVariable("idArticle") UUID idArticle,
                                        @PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                        @PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return auctionService.save(idArticle, from, to).map(AuctionMapper.INSTANCE::toAuctionDto);
    }

    @PutMapping("auctions/{id}/state")
    public Mono<AuctionDTO> updateAuctionState(@PathVariable String id, @RequestBody String state){
        return auctionService.updateAuctionState(id, state.toUpperCase()).map(AuctionMapper.INSTANCE::toAuctionDto);
    }

    @PutMapping("auctions/{id}/customer")
    public Mono<AuctionDTO> updateAuctionCustomer(@PathVariable String id, @RequestBody String customer){
        return auctionService.updateAuctionCustomer(id, customer).map(AuctionMapper.INSTANCE::toAuctionDto);
    }

    @PostMapping("auctions/{id}/bet")
    public Mono<AuctionDTO> updateAuctionBet(@PathVariable String id, @RequestBody String bet){
        return auctionService.updateAuctionBet(id, bet).map(AuctionMapper.INSTANCE::toAuctionDto);
    }
}
