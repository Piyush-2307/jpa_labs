package com.example.jap_labs.controller;

import com.example.jap_labs.dto.*;
import com.example.jap_labs.enums.Activity;
import com.example.jap_labs.enums.Gender;
import com.example.jap_labs.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        customerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateById(@PathVariable Long id, @Valid @RequestBody UpdateCustomerRequest request){
        return customerService.updateById(id, request);
    }

    @PatchMapping("/{id}/username")
    public CustomerResponse updateUsername(@PathVariable Long id, @Valid @RequestBody UpdateCustomerUsernameRequest request){
        return  customerService.updateUsername(id, request);
    }

    @PatchMapping("/{email}/email")
    public CustomerResponse updateEmail(@PathVariable String email, @Valid @RequestBody UpdateCustomerEmailRequest request){
        return customerService.updateEmail(email, request);
    }

    @PatchMapping("/{id}/password")
    public UpdatedCustomerPasswordResponse updatePassword(@PathVariable Long id, @Valid @RequestBody UpdateCustomerPasswordRequest request){
        return customerService.updatePassword(id, request);
    }

    @GetMapping("/gender")
    public List<CustomerResponse> findByGenderAndUsername(@RequestParam(required = false) Gender gender, @RequestParam(required = false) String username){
        return customerService.findByGenderAndUsername(gender, username);
    }

    @GetMapping("/summery")
    public List<CustomerSummary> findUserSummaries(@RequestParam(required = false) Gender gender){
        return customerService.findUserSummaries(gender);
    }

    @GetMapping("/count")
    public String countCustomer(){
        return "Total customers are = " + customerService.countCustomers();
    }

    @GetMapping("countByGender")
    public List<GenderCountResponse> countCustomerByGender(){
        return customerService.countCustomerByGender();
    }

    @PatchMapping("/activity")
    public int offlineCustomers(@RequestParam(required = false) Gender gender, @RequestParam(required = false) Activity activity){
        return customerService.offlineCustomers(gender, activity);
    }

    @PatchMapping("/activityNative/{id}")
    public ActivityStatusProjection updateActivityStatusNative(@PathVariable Long id,@RequestParam(required = false) Activity activity){
        return customerService.updateActivityStatusNative(id, activity);
    }
}
