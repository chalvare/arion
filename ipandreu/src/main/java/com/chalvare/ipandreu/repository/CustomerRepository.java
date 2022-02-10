package com.chalvare.ipandreu.repository;

import com.chalvare.ipandreu.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<CustomerEntity, String>{
    Flux<CustomerEntity> findByIdCustomer(String idCostumer);
}
