package com.example.identity.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class IntrospectResponse {
    private boolean isValid;
}
