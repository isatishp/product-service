package com.tomtom.productservice.repository;

import com.tomtom.productservice.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {


    List<Order> findByUserId(Long userId, Sort sort);
}
