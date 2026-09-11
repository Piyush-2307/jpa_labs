package com.example.jap_labs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCustomerPasswordRequest {
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
