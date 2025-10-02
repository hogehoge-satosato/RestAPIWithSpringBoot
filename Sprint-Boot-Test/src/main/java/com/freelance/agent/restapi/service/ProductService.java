package com.freelance.agent.restapi.service;

import java.util.List;

import com.freelance.agent.restapi.model.Product;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProduct(Long id);
    public void createProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProduct(Long id);
    public boolean exists(Long id);
}
