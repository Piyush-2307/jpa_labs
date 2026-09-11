package com.example.jap_labs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class UpdatedCustomerPasswordResponse {
    private String message;
    private Long id;
    private String email;
    private LocalTime time;
}
