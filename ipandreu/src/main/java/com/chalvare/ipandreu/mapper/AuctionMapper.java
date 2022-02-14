package com.chalvare.ipandreu.mapper;

import com.chalvare.ipandreu.domain.Auction;
import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.entity.AuctionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE  = Mappers.getMapper(AuctionMapper.class);

    Auction toAuction(AuctionEntity auctionEntity);

    AuctionEntity toAuctionEntity(Auction auction);

    AuctionDTO toAuctionDto(Auction auction);

}
