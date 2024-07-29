package com.dhlattanzio.agendapro.product.controller;

import com.dhlattanzio.agendapro.product.controller.dto.ProductDto;
import com.dhlattanzio.agendapro.product.controller.dto.filters.ProductFilters;
import com.dhlattanzio.agendapro.product.controller.dto.request.CreateProductRequest;
import com.dhlattanzio.agendapro.product.controller.dto.request.UpdateProductRequest;
import com.dhlattanzio.agendapro.product.service.ProductService;
import com.dhlattanzio.agendapro.shared.dto.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public PageResponse<ProductDto> getAll(Pageable pageable, ProductFilters filters) {
        return productService.getAll(pageable, filters);
    }

    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable Long id) {
        return productService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createOne(@RequestBody @Valid CreateProductRequest request) {
        ProductDto product = productService.createOne(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ProductDto updateOne(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest request) {
        return productService.updateOne(id, request);
    }

    @DeleteMapping("/{id}")
    public ProductDto deleteOne(@PathVariable Long id) {
        return productService.deleteOne(id);
    }
}
