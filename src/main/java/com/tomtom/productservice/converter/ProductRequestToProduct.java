package com.tomtom.productservice.converter;

import com.tomtom.productservice.controller.dto.ProductRequest;
import com.tomtom.productservice.model.Product;
import org.springframework.core.convert.converter.Converter;

public class ProductRequestToProduct implements Converter<ProductRequest, Product> {
    @Override
    public Product convert(ProductRequest source) {
        return Product.builder()
                .category(source.getCategory())
                .description(source.getDescription())
                .imageUrl(source.getImageUrl())
                .make(source.getMake())
                .name(source.getName())
                .price(source.getPrice())
                .build();
    }
}
