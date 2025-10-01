package com.freelance.agent.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.repository.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    
    public ProductServiceImpl(ProductMapper mapper) {
        this.productMapper = mapper;
    }
    
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }
    public Product getProduct(Long id) {
        return productMapper.findById(id);
    }
    public void createProduct(Product product) {
        productMapper.insert(product);
    }
    public void updateProduct(Product product) {
        productMapper.update(product);
    }
    public void deleteProduct(Long id) {
        productMapper.delete(id);
    }
}
