package com.example.jap_labs.mapper;

import com.example.jap_labs.dto.CreateCustomerRequest;
import com.example.jap_labs.dto.CustomerResponse;
import com.example.jap_labs.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CreateCustomerRequest request){
        return new Customer(request.getUsername(), request.getEmail(), request.getPassword(), request.getGender());
    }

    public CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(customer.getId(), customer.getUsername(), customer.getEmail());
    }
}
