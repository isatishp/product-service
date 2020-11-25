package com.tomtom.productservice.repository;

import com.tomtom.productservice.model.OrderItem;
import com.tomtom.productservice.model.OrderItemPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPK> {
}
