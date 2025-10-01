package com.freelance.agent.restapi.controler;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable Long id) {
        return productService.getProduct(id);
    }
    @PostMapping
    public void create(@RequestBody @Valid Product product) {
        productService.createProduct(product);
    }
    @PutMapping("/products/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid Product product) {
    	product.setId(id);
        productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
