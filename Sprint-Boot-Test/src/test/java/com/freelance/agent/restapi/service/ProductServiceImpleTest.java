package com.freelance.agent.restapi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.util.CsvDataSetLoader;
import com.freelance.agent.restapi.util.DbUnitTestConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@Import(DbUnitTestConfig.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
class ProductServiceImpleTest {

    @Autowired
    private ProductService productService;

    @Test
    @DatabaseSetup("/dataset/service/product/case1/")
    void testGetAll() {
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        assertEquals("テスト商品A", products.get(0).getName());
    }
    @Test
    @DatabaseSetup("/dataset/service/product/case2/")
    void testGetById() {
        long id = 2L;
        Product product = productService.getProduct(id);

        assertEquals("テスト商品B", product.getName());
    }
    @Test
    @DatabaseSetup("/dataset/service/product/case3/")
    void testCreate() {
        Product p = new Product();
        p.setName("テスト商品C");
        p.setPrice(BigDecimal.valueOf(100L));
        productService.createProduct(p);
        List<Product> products = productService.getAllProducts();

        assertEquals("テスト商品C", products.get(0).getName());
    }
    @Test
    @DatabaseSetup("/dataset/service/product/case4/")
    void testUpdateName() {
        long id = 1L;
        Product before = productService.getProduct(id);
        Product p = new Product();
        p.setId(id);
        p.setName("テスト商品D");
        p.setPrice(BigDecimal.valueOf(100L));
        productService.updateProduct(p);
        Product after = productService.getProduct(id);

        assertEquals("テスト商品A", before.getName());
        assertEquals("テスト商品D", after.getName());
    }
    @Test
    @DatabaseSetup("/dataset/service/product/case5/")
    void testDelete() {
        long id = 1L;
        Product productBefore = productService.getProduct(id);
        productService.deleteProduct(id);
        Product productAfter = productService.getProduct(id);
        assertEquals("テスト商品A", productBefore.getName());
        assertNull(productAfter);
    }
    @Test
    @DatabaseSetup("/dataset/service/product/case2/")
    void testExists() {
        long id1 = 1L;
        long id2 = 2L;
        boolean result1 = productService.exists(id1);
        boolean result2 = productService.exists(id2);
        assertFalse(result1);
        assertTrue(result2);
    }
}
