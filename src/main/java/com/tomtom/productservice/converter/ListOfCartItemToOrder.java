package com.tomtom.productservice.converter;

import com.tomtom.productservice.model.ListOfCartItem;
import com.tomtom.productservice.model.Order;
import com.tomtom.productservice.model.OrderItem;
import com.tomtom.productservice.model.OrderStatus;
import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.util.stream.Collectors;

public class ListOfCartItemToOrder implements Converter<ListOfCartItem, Order> {
    @Override
    public Order convert(ListOfCartItem source) {
        return Order.builder()
                .orderDate(Instant.now())
                .orderItems(
                        source.getCartItems().stream()
                                .map(cartItem -> new OrderItem(cartItem.getProduct(), cartItem.getQuantity()))
                                .collect(Collectors.toList()))
                .totalOrderPrice(source.getTotalAmountPayable())
                .status(OrderStatus.NEW)
                .build();
    }
}
