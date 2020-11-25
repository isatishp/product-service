package com.tomtom.productservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tomtom.productservice.model.ProductCategory;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {

    private ProductCategory category;

    private String make;

    private String name;

    private String description;

    private String imageUrl;

    private Double price;

}
