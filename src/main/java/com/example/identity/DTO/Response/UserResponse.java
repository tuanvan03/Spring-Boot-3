package com.example.identity.DTO.Response;

import com.example.identity.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    String username;
    String firstname;
    String lastName;
    LocalDate dob;
    Set<Role> roles;
}
