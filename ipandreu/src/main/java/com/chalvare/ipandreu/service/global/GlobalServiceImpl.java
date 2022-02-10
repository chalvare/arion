package com.chalvare.ipandreu.service.global;

import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.entity.AuctionEntity;
import com.chalvare.ipandreu.entity.CustomerEntity;
import com.chalvare.ipandreu.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GlobalServiceImpl implements GlobalService{

    private final CustomerRepository customerRepository;

    @Autowired
    public GlobalServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Flux<AuctionDTO> getAuctionsFromCustomer(String customerId) {
        return customerRepository.findByIdCustomer(customerId)
                .map(x -> x.getAuctions()
                        .stream()
                        .map(y -> new AuctionDTO(y.getIdAuction()))
                        .collect(Collectors.toList()))
                .flatMap(Flux::fromIterable);

    }
}
