package com.tomtom.productservice.filterspec;

import com.tomtom.productservice.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class ProductFilterSpecification implements Specification<Product> {

    private final ProductFilter productFilter;


    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (productFilter.getMake() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("make"), productFilter.getMake()));
        }
        if (productFilter.getCategory() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), productFilter.getCategory()));
        }
        if (productFilter.getName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), productFilter.getName()));
        }
        return predicate;
    }
}
