package com.chalvare.ipandreu.service.auction;

import com.chalvare.ipandreu.domain.Auction;
import com.chalvare.ipandreu.domain.AuctionState;
import com.chalvare.ipandreu.entity.AuctionEntity;
import com.chalvare.ipandreu.mapper.AuctionMapper;
import com.chalvare.ipandreu.repository.auction.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class AuctionServiceImpl implements AuctionService{

    private final AuctionRepository auctionRepository;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Mono<Auction> save(UUID idArticle, LocalDateTime from, LocalDateTime to) {
        return auctionRepository.save(AuctionMapper.INSTANCE.toAuctionEntity(buildAuction(idArticle, from, to))).map(AuctionMapper.INSTANCE::toAuction);
    }

    @Override
    public Mono<Auction> updateAuctionState(String id, String state) {
        return auctionRepository.updateState(id, state).map(AuctionMapper.INSTANCE::toAuction);
    }

    @Override
    public Mono<Auction> updateAuctionCustomer(String id, String customer) {
        return auctionRepository.updateCustomer(id, customer).map(AuctionMapper.INSTANCE::toAuction);
    }

    @Override
    public Mono<Auction> updateAuctionBet(String id, String bet) {
        return auctionRepository.updateBet(id, bet).map(AuctionMapper.INSTANCE::toAuction);
    }

    @Override
    public Mono<Auction> findById(String idAuction) {
        return auctionRepository.findById(idAuction).map(AuctionMapper.INSTANCE::toAuction);
    }

    @Override
    public Mono<Void> deleteById(String idAuction) {
        return auctionRepository.deleteById(idAuction);
    }


    private Auction buildAuction(UUID idArticle, LocalDateTime from, LocalDateTime to) {
        return Auction.builder()
                .idAuction(UUID.randomUUID().toString())
                .idArticle(idArticle.toString())
                .state(AuctionState.ACTIVE)
                .initDate(from.toInstant(ZoneOffset.UTC))
                .endDate(to.toInstant(ZoneOffset.UTC))
                .customers(new ArrayList<>())
                .bets(new ArrayList<>())
                .build();
    }
}
