package com.chalvare.ipandreu.dto;

import com.chalvare.ipandreu.domain.AuctionState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDTO {

    private String id;
    private String idAuction;
    private String idArticle;
    private Instant initDate;
    private Instant endDate;
    private AuctionState state;
    private List<String> bets;
    private List<String> customers;

    public AuctionDTO(String idAuction) {
        this.idAuction = idAuction;
    }
}
