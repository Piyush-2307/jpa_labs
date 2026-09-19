package com.example.jap_labs.dto;

import com.example.jap_labs.enums.Gender;

public record GenderCountResponse(
        Gender gender,
        Long totalCustomers
){}
