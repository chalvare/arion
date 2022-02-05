package com.chalvare.ipandreu.controller.dto;


import com.chalvare.ipandreu.service.domain.Article;
import com.chalvare.ipandreu.service.domain.Auction;
import com.chalvare.ipandreu.service.domain.Bet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO implements Serializable {
    private String id;
    private String name;
    private LocalDate birthday;
    private String email;
    private int age;
    private List<Auction> auctions;
    private List<Article>articles;
    private List<Bet>bets;
}
