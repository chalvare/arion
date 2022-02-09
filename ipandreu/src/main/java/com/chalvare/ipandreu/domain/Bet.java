package com.chalvare.ipandreu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bet implements Serializable {

    private String idBet;
    private BigDecimal amount;
    private Instant date;
    private String customerId;
    private String articleId;
    private String auctionId;
}

