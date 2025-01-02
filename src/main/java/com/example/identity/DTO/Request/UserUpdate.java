package com.example.identity.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdate {
    private String username;
    private String password;
    private String firstname;
    private String lastName;
    private LocalDate dob;
    List<String> roles;
}
