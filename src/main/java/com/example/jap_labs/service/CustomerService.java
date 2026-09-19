package com.example.jap_labs.service;

import com.example.jap_labs.dto.*;
import com.example.jap_labs.entity.Customer;
import com.example.jap_labs.enums.Activity;
import com.example.jap_labs.enums.Gender;
import com.example.jap_labs.mapper.CustomerMapper;
import com.example.jap_labs.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request){
        Customer customer = customerMapper.toEntity(request);
        customer.setActivity(Activity.ONLINE);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    public List<CustomerResponse> findAll(){
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customerList){
            CustomerResponse response = customerMapper.toResponse(customer);
            responses.add(response);
        }
        return responses;
    }

    public CustomerResponse findById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toResponse(customer);
    }

    public void deleteById(Long id){
        customerRepository.deleteById(id);
    }

    public CustomerResponse updateById(Long id, UpdateCustomerRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setUsername(request.getUsername());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    public CustomerResponse updateUsername(Long id, UpdateCustomerUsernameRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setUsername(request.getUsername());

        Customer savedUsername = customerRepository.save(customer);

        return customerMapper.toResponse(savedUsername);
    }

    public CustomerResponse updateEmail(String email, UpdateCustomerEmailRequest request){
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Customer"));

        customer.setEmail(request.getEmail());

        Customer savedEmail = customerRepository.save(customer);

        return customerMapper.toResponse(savedEmail);
    }

    public UpdatedCustomerPasswordResponse updatePassword(Long id, UpdateCustomerPasswordRequest request){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            throw new RuntimeException("Password not match");
        }

        customer.setPassword(request.getPassword());

        Customer updatedCustomer = customerRepository.save(customer);

        return new UpdatedCustomerPasswordResponse("The password has been changed", updatedCustomer.getId(), updatedCustomer.getEmail(), LocalTime.now());
    }

    public List<CustomerResponse> findByGenderAndUsername(Gender gender, String username){
        List<Customer> customers = customerRepository.findByGenderAndUsername(gender, username);
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers){
            CustomerResponse response = customerMapper.toResponse(customer);
            responses.add(response);
        }
        return responses;
    }

    public List<CustomerSummary> findUserSummaries(Gender gender){
        return customerRepository.findUserSummaries(gender);
    }

    public Long countCustomers(){
        return customerRepository.count();
    }

    public List<GenderCountResponse> countCustomerByGender(){
        return customerRepository.countCustomerByGender();
    }

    @Transactional
    public int offlineCustomers(Gender gender, Activity activity){
        return customerRepository.offlineCustomers(gender, activity);
    }

    public ActivityStatusProjection updateActivityStatusNative(Long id, Activity activity){
        return customerRepository.updateActivityStatusNative(id, activity.name());
    }
}
