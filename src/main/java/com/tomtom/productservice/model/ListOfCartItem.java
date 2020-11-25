package com.tomtom.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListOfCartItem {
    private List<CartItem> cartItems = new ArrayList<>();
    private Double totalAmountPayable;
}
