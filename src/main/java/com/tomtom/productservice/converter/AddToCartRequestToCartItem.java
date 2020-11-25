package com.tomtom.productservice.converter;

import com.tomtom.productservice.controller.dto.AddToCartRequest;
import com.tomtom.productservice.model.CartItem;
import com.tomtom.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class AddToCartRequestToCartItem implements Converter<AddToCartRequest, CartItem> {

    private final ProductRepository productRepository;

    @Override
    public CartItem convert(AddToCartRequest source) {
        return CartItem.builder()
                .quantity(source.getQuantity())
                .product(productRepository.findById(source.getProductId()).orElseThrow(() -> new RuntimeException("Invalid Product Id")))
                .build();
    }
}
