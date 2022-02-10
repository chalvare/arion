package com.chalvare.ipandreu.mapper;

import com.chalvare.ipandreu.domain.Customer;
import com.chalvare.ipandreu.dto.CustomerDTO;
import com.chalvare.ipandreu.entity.CustomerEntity;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;
import java.time.Instant;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE  = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomer(CustomerEntity customerEntity);

    CustomerEntity toCustomerEntity(Customer customer);

    CustomerDTO toCustomerDto(Customer customer);

    default Instant fromInstant(String date) {
        return date == null ? Instant.now() : Instant.parse(date);
    }

}
