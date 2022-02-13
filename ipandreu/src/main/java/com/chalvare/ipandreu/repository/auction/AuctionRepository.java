package com.chalvare.ipandreu.repository.auction;

import com.chalvare.ipandreu.entity.AuctionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends ReactiveMongoRepository<AuctionEntity, String>, CustomAuctionRepository{
}
