package com.chalvare.ipandreu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleEntity implements Serializable {

    private String idArticle;
    private String name;
    private String description;
    private BigDecimal initPrice;
    private BigDecimal actualPrice;
    private BigDecimal endPrice;
    List<String> bets;
    List<String> customers;
}
