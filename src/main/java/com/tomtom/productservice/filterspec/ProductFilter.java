package com.tomtom.productservice.filterspec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tomtom.productservice.model.ProductCategory;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductFilter {

    private String make;
    private String name;
    private ProductCategory category;

}
