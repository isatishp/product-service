package com.tomtom.productservice.controller;

import com.tomtom.productservice.controller.dto.AddToCartRequest;
import com.tomtom.productservice.model.CartItem;
import com.tomtom.productservice.model.ListOfCartItem;
import com.tomtom.productservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ListOfCartItem getItemsInCart(@RequestHeader Long userId) {
        return cartService.getItemsInCartForUser(userId);
    }

    @PostMapping
    public CartItem addToCart(@RequestBody AddToCartRequest addToCartRequest, @RequestHeader Long userId) {
        return cartService.addToCart(addToCartRequest, userId);
    }

    @DeleteMapping("/{cartItemId}")
    public ListOfCartItem deleteItemInCart(@PathVariable Long cartItemId, @RequestHeader Long userId) {
        return cartService.deleteItemInCart(cartItemId, userId);
    }
}
