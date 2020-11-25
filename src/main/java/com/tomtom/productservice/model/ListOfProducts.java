package com.tomtom.productservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ListOfProducts {
    private List<Product> products;
}
