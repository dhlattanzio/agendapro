package com.dhlattanzio.agendapro.product.specification;

import com.dhlattanzio.agendapro.product.controller.dto.filters.ProductFilters;
import com.dhlattanzio.agendapro.product.persistence.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> create(ProductFilters filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.name() != null) {
                predicates.add(cb.like(root.get("name"), "%" + filters.name() + "%"));
            }

            if (filters.description() != null) {
                predicates.add(cb.like(root.get("description"), "%" + filters.description() + "%"));
            }

            if (filters.category() != null) {
                predicates.add(cb.equal(root.get("category"), filters.category()));
            }

            if (filters.minPrice() != null && filters.maxPrice() != null) {
                predicates.add(cb.between(root.get("price"), filters.minPrice(), filters.maxPrice()));
            } else if (filters.minPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filters.minPrice()));
            } if (filters.maxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filters.maxPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
