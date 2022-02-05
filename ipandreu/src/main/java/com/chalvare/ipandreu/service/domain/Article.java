package com.chalvare.ipandreu.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal initPrice;
    private BigDecimal actualPrice;
    private BigDecimal endPrice;
    List<String> bets;
    List<String> customers;
}
