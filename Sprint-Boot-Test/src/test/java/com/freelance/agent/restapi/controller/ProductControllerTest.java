package com.freelance.agent.restapi.controller;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.freelance.agent.restapi.config.MockMvcConfig;
import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.service.ProductService;

@SpringBootTest
@Import(MockMvcConfig.class)
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private MessageSource messageSource;

    @Value("${TEST_USER}")
    private String USER;
    @Value("${TEST_PASSWORD}")
    private String PASS;

    @Test
    void testGetAllProducts() throws Exception {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("商品A");
        p1.setPrice(BigDecimal.valueOf(2000L));
        p1.setStock(20);
        p1.setDescription("説明A");
        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("商品B");
        p2.setPrice(BigDecimal.valueOf(3000L));
        p2.setStock(15);
        p2.setDescription("説明B");

        when(productService.getAllProducts()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/api/products")
               .with(httpBasic(USER, PASS)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("商品A"))
               .andExpect(jsonPath("$[1].name").value("商品B"));
    }

    @Test
    void testUpdateProductNotFound() throws Exception {
        Long id = 99L;
        Locale locale = Locale.JAPAN;

        when(productService.exists(id)).thenReturn(false);
        when(messageSource.getMessage("product.notfound.update", null, locale))
            .thenReturn("更新対象が見つかりません");

        mockMvc.perform(put("/api/products/{id}", id)
                .with(httpBasic(USER, PASS))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "更新商品",
                        "price": 2500,
                        "stock": 10,
                        "description": "更新説明"
                    }
                """)
                .locale(locale))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.message").value("更新対象が見つかりません"));
    }

    @Test
    void testCreateProduct() throws Exception {
        mockMvc.perform(post("/api/products")
                .with(httpBasic(USER, PASS))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "新商品",
                        "price": 1500,
                        "stock": 5,
                        "description": "新しい説明"
                    }
                """))
               .andExpect(status().isOk()); // or .isNoContent() if void
    }

    @Test
    void testDeleteProductNotFound() throws Exception {
        Long id = 99L;
        Locale locale = Locale.JAPAN;

        when(productService.exists(id)).thenReturn(false);
        when(messageSource.getMessage("product.notfound.delete", null, locale))
            .thenReturn("削除対象が見つかりません");

        mockMvc.perform(delete("/api/products/{id}", id)
                .with(httpBasic(USER, PASS))
                .with(csrf())
                .locale(locale))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.message").value("削除対象が見つかりません"));
    }

    @Test
    void testGetProduct() throws Exception {
    	long id = 1L;
        Product p1 = new Product();
        p1.setId(id);
        p1.setName("商品A");
        p1.setPrice(BigDecimal.valueOf(2000L));
        p1.setStock(20);
        p1.setDescription("説明A");

        when(productService.getProduct(id)).thenReturn(p1);

        mockMvc.perform(get("/api/products/{id}", id)
               .with(httpBasic(USER, PASS)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("商品A"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        long id = 1L;
        when(productService.exists(id)).thenReturn(true);

        mockMvc.perform(put("/api/products/{id}", id)
                .with(httpBasic(USER, PASS))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "更新商品",
                        "price": 2500,
                        "stock": 10,
                        "description": "更新説明"
                    }
                """))
                .andExpect(status().isNoContent()); 
    }

    @Test
    void testDeleteProduct() throws Exception {
        long id = 1L;
        when(productService.exists(id)).thenReturn(true);

        mockMvc.perform(delete("/api/products/{id}", id)
                .with(httpBasic(USER, PASS))
                .with(csrf()))
                .andExpect(status().isNoContent()); 
    }
    @Test
    void testValidationErrorResponse() throws Exception {
        long id = 1L;
        when(productService.exists(id)).thenReturn(true);

        mockMvc.perform(put("/api/products/{id}", id)
                .with(httpBasic(USER, PASS))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "",
                        "stock": -1,
                        "price": 0
                    }
                """))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.name").value("商品名は必須です"))
            .andExpect(jsonPath("$.price").value("価格は1以上である必要があります"))
            .andExpect(jsonPath("$.stock").value("在庫は0以上である必要があります"));
    }

}
