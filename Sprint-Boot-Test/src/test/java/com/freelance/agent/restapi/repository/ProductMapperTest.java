package com.freelance.agent.restapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;

import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.util.CsvDataSetLoader;
import com.freelance.agent.restapi.util.DbUnitTestConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@Import(DbUnitTestConfig.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    @DatabaseSetup("/dataset/repository/product/case1/")
    void testCase1() {
        List<Product> products = productMapper.findAll();
        assertEquals(2, products.size());
        assertEquals("テスト商品A", products.get(0).getName());
    }

    @Test
    @DatabaseSetup("/dataset/repository/product/case2/")
    void testCase2() {
        long id = 2L;
        Product product = productMapper.findById(id);
        assertEquals("テスト商品B", product.getName());
    }

    @Test
    @DatabaseSetup("/dataset/repository/product/case3/")
    void testCase3() {
        Product product = new Product();
        product.setName("テスト商品C");
        product.setPrice(BigDecimal.valueOf(100L));
        productMapper.insert(product);
        List<Product> products = productMapper.findAll();
        assertEquals("テスト商品C", products.get(0).getName());
    }

    @Test
    @DatabaseSetup("/dataset/repository/product/case4/")
    void testCase4() {
        long id = 1L;
        Product productBefore = productMapper.findById(id);
        Product product = new Product();
        product.setId(id);
        product.setName("テスト商品D");
        product.setPrice(BigDecimal.valueOf(100L));
        productMapper.update(product);
        Product productAfter = productMapper.findById(id);
        assertEquals("テスト商品A", productBefore.getName());
        assertEquals("テスト商品D", productAfter.getName());
    }

    @Test
    @DatabaseSetup("/dataset/repository/product/case5/")
    void testCase5() {
        long id = 1L;
        Product productBefore = productMapper.findById(id);
        productMapper.delete(id);
        Product productAfter = productMapper.findById(id);
        assertEquals("テスト商品A", productBefore.getName());
        assertNull(productAfter);
    }
}

