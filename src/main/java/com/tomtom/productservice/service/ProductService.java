package com.tomtom.productservice.service;

import com.tomtom.productservice.controller.dto.ProductRequest;
import com.tomtom.productservice.filterspec.ProductFilter;
import com.tomtom.productservice.filterspec.ProductFilterSpecification;
import com.tomtom.productservice.model.Product;
import com.tomtom.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    public List<Product> getProductsByCategory(ProductFilter productFilter, Pageable pageable) {
        Page<Product> products;
        if (productFilter == null) {
            products = productRepository.findAll(pageable);
        } else {
            ProductFilterSpecification specification = new ProductFilterSpecification(productFilter);
            products = productRepository.findAll(specification, pageable);
        }
        return products.hasContent() ? products.getContent() : Collections.emptyList();
    }

    public Product addProduct(ProductRequest productRequest, Long sellerId) {
        Product product = conversionService.convert(productRequest, Product.class);
        assert product != null;
        product.setSellerId(sellerId);
        return productRepository.save(product);
    }
}
