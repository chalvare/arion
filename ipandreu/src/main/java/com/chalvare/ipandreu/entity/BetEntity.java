package com.chalvare.ipandreu.entity;

import com.chalvare.ipandreu.domain.BetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetEntity implements Serializable {

    private String idBet;
    private BigDecimal amount;
    private String state;
    private String date;
    private String customerId;
    private String articleId;
    private String auctionId;
}

