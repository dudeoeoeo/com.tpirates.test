package com.rest.test.web;

import com.rest.test.domain.delivery.Delivery;
import com.rest.test.domain.product.Product;
import com.rest.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/v1/products")
    public List<Map<String, String>> findAll() {
        System.out.println("products findAll called");
        return productService.findAll();
    }

    @GetMapping("/api/v1/product_detail/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        System.out.println("product_detail called");
        return productService.findById(id);
    }

    @PostMapping("/api/v1/product_add")
    public Product newProduct(@RequestBody Product newProduct) {
        System.out.println("newProduct 호출");
        return productService.save(newProduct);
    }

    @GetMapping("/api/v1/delivery_date/{id}")
    public List<Map<String, String>> delivery(@PathVariable Long id) {
        return productService.delivery(id);
    }

    @DeleteMapping("/api/v1/remove/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
