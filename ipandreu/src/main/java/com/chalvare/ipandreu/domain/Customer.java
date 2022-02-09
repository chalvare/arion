package com.chalvare.ipandreu.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {

    private String idCustomer;
    private String name;
    private Instant birthday;
    private String email;
    private int age;
    private List<Auction> auctions;
    private List<Article>articles;
    private List<Bet>bets;

}
