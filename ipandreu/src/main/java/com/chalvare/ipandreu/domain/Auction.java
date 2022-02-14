package com.chalvare.ipandreu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction implements Serializable {

    private String id;
    private String idAuction;
    private String idArticle;
    private Instant initDate;
    private Instant endDate;
    private AuctionState state;
    private List<String> bets;
    private List<String> customers;

}

