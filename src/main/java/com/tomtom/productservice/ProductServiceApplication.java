package com.tomtom.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomtom.productservice.model.ListOfProducts;
import com.tomtom.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class ProductServiceApplication {

    @Value("classpath:products.json")
    private Resource resource;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ProductRepository repository) {
        return args -> {
            ListOfProducts products = new ObjectMapper().readValue(resource.getFile(), ListOfProducts.class);
            repository.saveAll(products.getProducts());
        };
    }

}
