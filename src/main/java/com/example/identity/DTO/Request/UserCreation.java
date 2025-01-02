package com.example.identity.DTO.Request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreation {
    @Size(min = 3, message = "USER_EXITS")
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;
    String firstname;
    String lastName;
    LocalDate dob;
}
