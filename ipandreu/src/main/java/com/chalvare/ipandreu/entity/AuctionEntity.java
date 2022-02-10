package com.chalvare.ipandreu.entity;

import com.chalvare.ipandreu.domain.AuctionState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionEntity implements Serializable {

    private String idAuction;
    private String articleId;
    private String initDate;
    private String endDate;
    private String state;
    private List<String> bets;
    private List<String> customers;


}

