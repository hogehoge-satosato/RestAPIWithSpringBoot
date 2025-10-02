package com.freelance.agent.restapi.controler;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.agent.restapi.advice.ValidationExceptionHandler;
import com.freelance.agent.restapi.model.Product;
import com.freelance.agent.restapi.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    private final ProductService productService;
    private final MessageSource messageSource;
    public ProductController(ProductService service, MessageSource source) {
        this.productService = service;
        this.messageSource = source;
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
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Product product, Locale locale) {
        if (!productService.exists(id)) {
            String msg = messageSource.getMessage("product.notfound.update", null, locale);
            log.warn("エラー: " + msg );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body(Map.of( "message" , msg));
        }
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id, Locale locale) {
         if (!productService.exists(id)) {
             String msg = messageSource.getMessage("product.notfound.delete", null, locale);
             log.warn("エラー: " + msg );
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(Map.of( "message" , msg));
         }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
