package com.chalvare.ipandreu.service.global;

import com.chalvare.ipandreu.dto.AuctionDTO;
import com.chalvare.ipandreu.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

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
