package com.chalvare.ipandreu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetEntity implements Serializable {

    private String idBet;
    private BigDecimal amount;
    private String date;
    private String customerId;
    private String articleId;
    private String auctionId;
}

