package com.tomtom.productservice.repository;

import com.tomtom.productservice.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);

    Optional<CartItem> findByCartItemIdAndUserId(Long cartItemId, Long userId);
}
