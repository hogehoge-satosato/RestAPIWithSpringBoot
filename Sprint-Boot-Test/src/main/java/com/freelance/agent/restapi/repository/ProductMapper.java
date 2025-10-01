package com.freelance.agent.restapi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.freelance.agent.restapi.model.Product;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Product findById(Long id);
    void insert(Product product);
    void update(Product product);
    void delete(Long id);
}
