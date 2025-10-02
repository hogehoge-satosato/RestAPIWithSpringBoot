package com.freelance.agent.restapi.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    
    @NotBlank(message = "{NotBlank.productRequest.name}")
    private String name;

    @DecimalMin(value ="1.0", message = "{Min.productRequest.price}")
    private BigDecimal price;

    @Min(0)
    private int stock;

    private String description;
}
