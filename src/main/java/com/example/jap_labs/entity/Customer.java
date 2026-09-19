package com.example.jap_labs.entity;

import com.example.jap_labs.enums.Activity;
import com.example.jap_labs.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "activity")
    @Enumerated(EnumType.STRING)
    private Activity activity;

    public Customer(String username, String email, String password, Gender gender, Activity activity){
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.activity = activity;
    }
}
