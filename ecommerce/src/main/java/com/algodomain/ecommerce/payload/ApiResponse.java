package com.algodomain.ecommerce.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private String message;
    private boolean success;
}

