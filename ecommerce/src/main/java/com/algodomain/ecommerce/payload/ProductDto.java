package com.algodomain.ecommerce.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Name must be min of 4 characters")
    private String name;

    @NotEmpty
    @Size(min = 4, message = "Name must be min of 4 characters")
    private String description;

    @NotBlank(message = "Image name should not be blank")
    private String productImage;

    @NotNull
    private int price;
}
