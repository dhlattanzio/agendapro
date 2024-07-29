package com.dhlattanzio.agendapro.product.mapper;

import com.dhlattanzio.agendapro.product.controller.dto.ProductDto;
import com.dhlattanzio.agendapro.product.event.ProductCreatedEvent;
import com.dhlattanzio.agendapro.product.event.ProductDeletedEvent;
import com.dhlattanzio.agendapro.product.event.ProductUpdatedEvent;
import com.dhlattanzio.agendapro.product.persistence.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductMapperTests {
    @InjectMocks
    private ProductMapper productMapper;

    @Test
    public void whenMapEntityToDto_thenReturnProductDto() {
        Product product = createProduct();

        ProductDto productDto = productMapper.toDto(product);

        assertNotNull(productDto);
        assertEquals(product.getName(), productDto.name());
        assertEquals(product.getDescription(), productDto.description());
        assertEquals(product.getCategory(), productDto.category());
        assertEquals(product.getPrice(), productDto.price());
    }

    @Test
    public void whenMapEntityToCreateEvent_thenReturnProductCreatedEvent() {
        Product product = createProduct();

        ProductCreatedEvent productCreatedEvent = productMapper.toCreatedEvent(product);

        assertNotNull(productCreatedEvent);
        assertEquals(product.getId(), productCreatedEvent.id());
        assertEquals(product.getName(), productCreatedEvent.name());
        assertEquals(product.getDescription(), productCreatedEvent.description());
        assertEquals(product.getCategory(), productCreatedEvent.category());
        assertEquals(product.getPrice(), productCreatedEvent.price());
    }

    @Test
    public void whenMapEntityToUpdateEvent_thenReturnProductUpdatedEvent() {
        String previousCategory = "PreviousCategory";
        Product product = createProduct();

        ProductUpdatedEvent productUpdatedEvent = productMapper.toUpdatedEvent(product, previousCategory);

        assertNotNull(productUpdatedEvent);
        assertEquals(product.getId(), productUpdatedEvent.id());
        assertEquals(product.getCategory(), productUpdatedEvent.category());
        assertEquals(previousCategory, productUpdatedEvent.previousCategory());
    }

    @Test
    public void whenMapEntityToDeleteEvent_thenReturnProductDeletedEvent() {
        Product product = createProduct();

        ProductDeletedEvent productDeletedEvent = productMapper.toDeletedEvent(product);

        assertNotNull(productDeletedEvent);
        assertEquals(product.getId(), productDeletedEvent.id());
        assertEquals(product.getCategory(), productDeletedEvent.category());
    }

    private Product createProduct() {
        Product product = new Product();
        product.setName("Product");
        product.setDescription("Description");
        product.setCategory("Category");
        product.setPrice(BigDecimal.valueOf(10L));
        return product;
    }
}
