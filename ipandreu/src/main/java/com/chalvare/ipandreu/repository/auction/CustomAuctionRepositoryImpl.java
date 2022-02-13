package com.chalvare.ipandreu.repository.auction;

import com.chalvare.ipandreu.entity.AuctionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CustomAuctionRepositoryImpl implements  CustomAuctionRepository{

    public static final String ID = "_id";
    public static final String STATE = "state";
    public static final String CUSTOMERS = "customers";
    public static final String BETS = "bets";
    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomAuctionRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<AuctionEntity> updateState(String id, String state) {
        return mongoTemplate.findAndModify(
                new Query(Criteria.where(ID).is(id)),
                new Update().set(STATE, state),
                new FindAndModifyOptions().returnNew(true),
                AuctionEntity.class);
    }

    @Override
    public Mono<AuctionEntity> updateCustomer(String id, String customer) {
        return mongoTemplate.findAndModify(
                new Query(Criteria.where(ID).is(id)),
                new Update().addToSet(CUSTOMERS, customer),
                new FindAndModifyOptions().returnNew(true),
                AuctionEntity.class);
    }

    @Override
    public Mono<AuctionEntity> updateBet(String id, String bet) {
        return mongoTemplate.findAndModify(
                new Query(Criteria.where(ID).is(id)),
                new Update().addToSet(BETS, bet),
                new FindAndModifyOptions().returnNew(true),
                AuctionEntity.class);
    }
}
