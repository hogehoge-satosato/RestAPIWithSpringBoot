package com.freelance.agent.restapi.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void shouldRejectEmptyName() {
        long price = 100;
        Product product = new Product();
        product.setId(1L);
        product.setName("");
        product.setPrice(BigDecimal.valueOf(price));
        product.setDescription("テスト");

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty(), "商品名が空文字の場合はバリデーション違反になるべき");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }
    @Test
    void shouldRejectNegativePrice() {
        long price = -100;
        Product product = new Product();
        product.setName("テスト商品");
        product.setPrice(BigDecimal.valueOf(price));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty(), "価格が負の場合はバリデーション違反になるべき");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("price")));
    }
    @Test
    void shouldRejectNegativeStock() {
        int stock = -100;
        Product product = new Product();
        product.setName("テスト商品");
        product.setStock(stock);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty(), "在庫が負の場合はバリデーション違反になるべき");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("stock")));
    }
    
    @Test
    void shouldTouchProductDirectly() {
        Product product = new Product();
        product.setName("テスト商品");
        product.setPrice(BigDecimal.valueOf(100));
        product.setStock(10);
        product.setDescription("説明");
        product.setId(1L);

        assertEquals("テスト商品", product.getName());
        assertEquals(BigDecimal.valueOf(100), product.getPrice());
        assertEquals(10, product.getStock());
        assertEquals("説明", product.getDescription());
        assertEquals(1L, product.getId());
    }
}
