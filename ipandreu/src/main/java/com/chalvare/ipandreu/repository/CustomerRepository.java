package com.chalvare.ipandreu.repository;

import com.chalvare.ipandreu.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<CustomerEntity, String>{
}
