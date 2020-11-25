package com.tomtom.productservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddToCartRequest {

    private Long productId;

    private Integer quantity;

}
