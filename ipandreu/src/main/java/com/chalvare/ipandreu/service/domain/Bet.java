package com.chalvare.ipandreu.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bet {
    @Id
    private String id;
    private BigDecimal amount;
    private LocalDate date;
    private String customerId;
    private String articleId;
    private String auctionId;
}

