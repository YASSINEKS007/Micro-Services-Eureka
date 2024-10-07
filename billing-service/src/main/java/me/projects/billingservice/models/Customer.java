package me.projects.billingservice.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
}
