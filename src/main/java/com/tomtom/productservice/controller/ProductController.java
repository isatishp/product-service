package com.tomtom.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomtom.productservice.controller.dto.ProductRequest;
import com.tomtom.productservice.filterspec.ProductFilter;
import com.tomtom.productservice.model.Product;
import com.tomtom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ObjectMapper objectMapper;

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) Map<String, String> map, Pageable pageable) {
        return productService.getProductsByCategory(objectMapper.convertValue(map, ProductFilter.class), pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody ProductRequest productRequest, @RequestHeader Long sellerId) {
        return productService.addProduct(productRequest, sellerId);
    }


}
