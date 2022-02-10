package com.chalvare.ipandreu.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customers")
public class CustomerEntity implements Serializable {
    //TODO eliminar datos personales (name, birthday, email y edad)
    @Indexed(unique=true)
    private String idCustomer;
    private String name;
    private String birthday;
    private String state;
    private String email;
    private int age;
    private List<AuctionEntity> auctions;
    private List<ArticleEntity>articles;
    private List<BetEntity>bets;
}
