package com.algodomain.ecommerce.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;

    private String password;

}
