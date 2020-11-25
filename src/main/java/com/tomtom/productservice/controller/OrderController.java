package com.tomtom.productservice.controller;

import com.tomtom.productservice.model.Order;
import com.tomtom.productservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getOrderHistory(@RequestHeader Long userId) {
        return orderService.getOrders(userId);
    }

    @PostMapping
    public Order createOrder(@RequestHeader Long userId) {
        return orderService.createOrder(userId);
    }
}
