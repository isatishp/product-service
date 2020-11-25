package com.tomtom.productservice.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tomtom.productservice.converter.AddToCartRequestToCartItem;
import com.tomtom.productservice.converter.ListOfCartItemToOrder;
import com.tomtom.productservice.converter.ProductRequestToProduct;
import com.tomtom.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return objectMapper;
    }

    @Bean
    public ConversionService conversionService(ProductRepository productRepository) {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new ProductRequestToProduct());
        conversionService.addConverter(new AddToCartRequestToCartItem(productRepository));
        conversionService.addConverter(new ListOfCartItemToOrder());
        return conversionService;
    }


}
