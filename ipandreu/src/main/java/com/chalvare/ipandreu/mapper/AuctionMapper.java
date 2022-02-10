package com.chalvare.ipandreu.mapper;

import com.chalvare.ipandreu.domain.Auction;
import com.chalvare.ipandreu.domain.Customer;
import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.dto.CustomerDTO;
import com.chalvare.ipandreu.entity.AuctionEntity;
import com.chalvare.ipandreu.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE  = Mappers.getMapper(AuctionMapper.class);

    Auction toAuction(AuctionEntity auctionEntity);

    AuctionEntity toAuctionEntity(Auction auction);

    AuctionDTO toAuctionDto(Auction auction);

    default Instant fromInstant(String date) {
        return date == null ? Instant.now() : Instant.parse(date);
    }

}
