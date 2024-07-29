package com.dhlattanzio.agendapro.product.service.impl;

import com.dhlattanzio.agendapro.product.controller.dto.ProductDto;
import com.dhlattanzio.agendapro.product.controller.dto.filters.ProductFilters;
import com.dhlattanzio.agendapro.product.controller.dto.request.CreateProductRequest;
import com.dhlattanzio.agendapro.product.controller.dto.request.UpdateProductRequest;
import com.dhlattanzio.agendapro.product.mapper.ProductMapper;
import com.dhlattanzio.agendapro.product.persistence.entity.Product;
import com.dhlattanzio.agendapro.product.persistence.repository.ProductRepository;
import com.dhlattanzio.agendapro.product.service.ProductService;
import com.dhlattanzio.agendapro.product.specification.ProductSpecification;
import com.dhlattanzio.agendapro.shared.dto.PageResponse;
import com.dhlattanzio.agendapro.shared.event.publisher.EventPublisher;
import com.dhlattanzio.agendapro.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final EventPublisher eventPublisher;

    @Override
    public ProductDto getOne(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Product id '%d' not found".formatted(id)));
    }

    @Override
    public PageResponse<ProductDto> getAll(Pageable pageable, ProductFilters filters) {
        Specification<Product> specification = ProductSpecification.create(filters);
        Page<Product> productsPage = productRepository.findAll(specification, pageable);

        return new PageResponse<>(
                productsPage.getNumber(), productsPage.getTotalPages(), productsPage.getTotalElements(),
                productsPage.getContent().stream().map(productMapper::toDto).toList()
        );
    }

    @Transactional
    @Override
    public ProductDto createOne(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setPrice(request.price());

        productRepository.save(product);
        eventPublisher.publish("product.created", productMapper.toCreatedEvent(product));

        log.info("Product created: {}", product.getId());

        return productMapper.toDto(product);
    }

    @Transactional
    @Override
    public ProductDto updateOne(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product id '%d' not found".formatted(id)));
        String previousCategory = product.getCategory();

        product.setName(request.name());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setPrice(request.price());

        productRepository.save(product);
        eventPublisher.publish("product.updated", productMapper.toUpdatedEvent(product, previousCategory));

        log.info("Product updated: {}", product.getId());

        return productMapper.toDto(product);
    }

    @Transactional
    @Override
    public ProductDto deleteOne(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product id '%d' not found".formatted(id)));

        productRepository.delete(product);
        eventPublisher.publish("product.deleted", productMapper.toDeletedEvent(product));

        log.info("Product deleted: {}", product.getId());

        return productMapper.toDto(product);
    }
}
