package com.example.jap_labs.dto;

import com.example.jap_labs.enums.Activity;
import com.example.jap_labs.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCustomerRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Gender gender;

    private Activity activity;
}
