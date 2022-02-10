package com.chalvare.ipandreu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetEntity implements Serializable {

    @Indexed(unique=true)
    private String idBet;
    private BigDecimal amount;
    private String state;
    private String date;
    private String idCustomer;
    private String idArticle;
    private String idAuction;
}

