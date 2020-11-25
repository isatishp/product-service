package com.tomtom.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {

    @EmbeddedId
    @JsonIgnore
    private OrderItemPK pk;

    private Integer quantity;

    public OrderItem(Product product, Integer quantity) {
        pk = new OrderItemPK();
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }
}
