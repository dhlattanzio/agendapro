package com.dhlattanzio.agendapro.product.service;

import com.dhlattanzio.agendapro.product.constant.ProductTopic;
import com.dhlattanzio.agendapro.product.controller.dto.ProductDto;
import com.dhlattanzio.agendapro.product.controller.dto.filters.ProductFilters;
import com.dhlattanzio.agendapro.product.controller.dto.request.CreateProductRequest;
import com.dhlattanzio.agendapro.product.controller.dto.request.UpdateProductRequest;
import com.dhlattanzio.agendapro.product.mapper.ProductMapper;
import com.dhlattanzio.agendapro.product.persistence.entity.Product;
import com.dhlattanzio.agendapro.product.persistence.repository.ProductRepository;
import com.dhlattanzio.agendapro.product.service.impl.ProductServiceImpl;
import com.dhlattanzio.agendapro.shared.dto.PageResponse;
import com.dhlattanzio.agendapro.shared.event.publisher.EventPublisher;
import com.dhlattanzio.agendapro.shared.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private EventPublisher eventPublisher;

    @Test
    public void whenGetOne_thenReturnProduct() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDto productDto = ProductDto.builder().id(1L).build();
        when(productMapper.toDto(product)).thenReturn(productDto);

        assertDoesNotThrow(() -> {
            ProductDto result = productService.getOne(1L);
            assertNotNull(result);
            assertEquals(productDto, result);
            verify(productRepository, times(1)).findById(1L);
        });
    }

    @Test
    public void whenGetOneButNotFound_thenThrowNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            productService.getOne(1L);
        });
    }

    @Test
    public void whenGetAll_thenReturnPageOfProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll(ArgumentMatchers.<Specification<Product>>any(), any(Pageable.class)))
                .thenReturn(new PageImpl<Product>(List.of(product1, product2)));
        ProductDto productDto1 = ProductDto.builder().id(1L).build();
        ProductDto productDto2 = ProductDto.builder().id(2L).build();
        when(productMapper.toDto(product1)).thenReturn(productDto1);
        when(productMapper.toDto(product2)).thenReturn(productDto2);

        PageResponse<ProductDto> result =
                productService.getAll(Pageable.ofSize(5), ProductFilters.builder().build());
        assertNotNull(result);
        assertEquals(0, result.page());
        assertEquals(1, result.totalPages());
        assertEquals(2L, result.totalItems());
        assertEquals(1L, result.items().get(0).id());
        assertEquals(2L, result.items().get(1).id());
        verify(productRepository, times(1))
                .findAll(ArgumentMatchers.<Specification<Product>>any(), any(Pageable.class));
    }

    @Test
    public void whenCreateOne_thenReturnProduct() {
        Product product = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductDto productDto = ProductDto.builder().id(1L).build();
        when(productMapper.toDto(any(Product.class))).thenReturn(productDto);

        ProductDto result = productService.createOne(CreateProductRequest.builder().build());
        assertNotNull(result);
        assertEquals(productDto, result);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void whenCreateOne_thenPublishCreatedEvent() {
        Product product = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductDto productDto = ProductDto.builder().id(1L).build();
        when(productMapper.toDto(any(Product.class))).thenReturn(productDto);

        productService.createOne(CreateProductRequest.builder().build());
        verify(eventPublisher, times(1)).publish(eq(ProductTopic.CREATED), any());
    }

    @Test
    public void whenUpdateOne_thenReturnProduct() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDto productDto = ProductDto.builder().id(1L).build();
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.updateOne(1L, UpdateProductRequest.builder().build());
        assertNotNull(result);
        assertEquals(productDto, result);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void whenUpdateOneButNotFound_thenThrowNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            productService.updateOne(1L, UpdateProductRequest.builder().build());
        });
    }

    @Test
    public void whenUpdateOne_thenPublishUpdatedEvent() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDto productDto = ProductDto.builder().id(1L).build();
        when(productMapper.toDto(product)).thenReturn(productDto);

        productService.updateOne(1L, UpdateProductRequest.builder().build());
        verify(eventPublisher, times(1)).publish(eq(ProductTopic.UPDATED), any());
    }

    @Test
    public void whenDeleteOne_thenReturnProduct() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDto productDto = ProductDto.builder().id(1L).build();
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.deleteOne(1L);
        assertNotNull(result);
        assertEquals(productDto, result);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void whenDeleteOneButNotFound_thenThrowNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            productService.deleteOne(1L);
        });
    }

    @Test
    public void whenDeleteOne_thenPublishDeletedEvent() {
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDto productDto = ProductDto.builder().id(1L).build();
        when(productMapper.toDto(product)).thenReturn(productDto);

        productService.deleteOne(1L);
        verify(eventPublisher, times(1)).publish(eq(ProductTopic.DELETED), any());
    }
}
