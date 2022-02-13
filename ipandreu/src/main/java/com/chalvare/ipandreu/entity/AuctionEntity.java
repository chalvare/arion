package com.chalvare.ipandreu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "auctions")
public class AuctionEntity implements Serializable {

    @Id
    private String id;
    private String idAuction;
    private String idArticle;
    private String initDate;
    private String endDate;
    private String state;
    private List<String> bets;
    private List<String> customers;


}

