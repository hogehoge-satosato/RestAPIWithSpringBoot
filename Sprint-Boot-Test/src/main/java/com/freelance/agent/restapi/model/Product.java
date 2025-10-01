package com.freelance.agent.restapi.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Product {

    private Long id;
    
    @NotBlank
    private String name;

    @DecimalMin("0.0")
    private BigDecimal price;

    @Min(0)
    private int stock;

    private String description;
}
