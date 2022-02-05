package com.chalvare.ipandreu.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
    @Id
    private String id;
    private String articleId;
    private LocalDate initDate;
    private LocalDate endDate;
    private List<String> bets;
    private List<String> customers;


}

