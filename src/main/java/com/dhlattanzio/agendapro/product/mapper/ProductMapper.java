package com.dhlattanzio.agendapro.product.mapper;

import com.dhlattanzio.agendapro.product.controller.dto.ProductDto;
import com.dhlattanzio.agendapro.product.event.ProductCreatedEvent;
import com.dhlattanzio.agendapro.product.event.ProductDeletedEvent;
import com.dhlattanzio.agendapro.product.event.ProductUpdatedEvent;
import com.dhlattanzio.agendapro.product.persistence.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice()
        );
    }

    public ProductCreatedEvent toCreatedEvent(Product product) {
        return new ProductCreatedEvent(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice()
        );
    }

    public ProductUpdatedEvent toUpdatedEvent(Product product, String previousCategory) {
        return new ProductUpdatedEvent(
                product.getId(),
                product.getCategory(),
                previousCategory
        );
    }

    public ProductDeletedEvent toDeletedEvent(Product product) {
        return new ProductDeletedEvent(
                product.getId(),
                product.getCategory()
        );
    }
}
