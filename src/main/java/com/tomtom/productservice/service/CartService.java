package com.tomtom.productservice.service;

import com.tomtom.productservice.controller.dto.AddToCartRequest;
import com.tomtom.productservice.model.CartItem;
import com.tomtom.productservice.model.ListOfCartItem;
import com.tomtom.productservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final ConversionService conversionService;

    public ListOfCartItem getItemsInCartForUser(Long userId) {
        List<CartItem> cartItems = cartRepository.findByUserId(userId);
        return ListOfCartItem.builder()
                .cartItems(cartItems)
                .totalAmountPayable(cartItems.stream()
                        .map(CartItem::getAmount)
                        .reduce(0.0, Double::sum))
                .build();
    }

    public CartItem addToCart(AddToCartRequest addToCartRequest, Long userId) {
        CartItem cartItem = conversionService.convert(addToCartRequest, CartItem.class);
        assert cartItem != null;
        cartItem.setUserId(userId);
        cartItem.setAmount(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        return cartRepository.save(cartItem);
    }

    public ListOfCartItem deleteItemInCart(Long cartItemId, Long userId) {
        CartItem cartItem = cartRepository.findByCartItemIdAndUserId(cartItemId, userId).orElseThrow(() -> new RuntimeException("Cart Item Not Found"));
        cartRepository.delete(cartItem);
        return getItemsInCartForUser(userId);
    }
}
