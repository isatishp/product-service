package com.tomtom.productservice.service;

import com.tomtom.productservice.model.ListOfCartItem;
import com.tomtom.productservice.model.Order;
import com.tomtom.productservice.repository.OrderItemRepository;
import com.tomtom.productservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final ConversionService conversionService;

    private final OrderItemRepository orderItemRepository;

    public List<Order> getOrders(Long userId) {

        return orderRepository.findByUserId(userId, Sort.by("id").descending());
    }

    public Order createOrder(Long userId) {
        ListOfCartItem listOfCartItem = cartService.getItemsInCartForUser(userId);
        if (listOfCartItem.getCartItems().isEmpty()) {
            throw new RuntimeException("Empty Cart!");
        }
        Order order = conversionService.convert(listOfCartItem, Order.class);
        assert order != null;
        order.setUserId(userId);
        order.getOrderItems().forEach(orderItem -> {
            orderItem.getPk().setOrder(order);
        });
        Order savedOrder = orderRepository.save(order);
        savedOrder.getOrderItems().forEach(orderItemRepository::save);
        listOfCartItem.getCartItems()
                .forEach(cartItem -> cartService.deleteItemInCart(cartItem.getCartItemId(), userId));
        return savedOrder;
    }
}
