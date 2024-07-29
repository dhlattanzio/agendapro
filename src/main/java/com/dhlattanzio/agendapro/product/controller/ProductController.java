package com.dhlattanzio.agendapro.product.controller;

import com.dhlattanzio.agendapro.product.controller.dto.ProductDto;
import com.dhlattanzio.agendapro.product.controller.dto.filters.ProductFilters;
import com.dhlattanzio.agendapro.product.controller.dto.request.CreateProductRequest;
import com.dhlattanzio.agendapro.product.controller.dto.request.UpdateProductRequest;
import com.dhlattanzio.agendapro.product.service.ProductService;
import com.dhlattanzio.agendapro.shared.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public PageResponse<ProductDto> getAll(@ParameterObject Pageable pageable,
                                           @ParameterObject ProductFilters filters) {
        return productService.getAll(pageable, filters);
    }

    @Operation(summary = "Get one product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable Long id) {
        return productService.getOne(id);
    }

    @Operation(summary = "Create one product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProductDto> createOne(@RequestBody @Valid CreateProductRequest request) {
        ProductDto product = productService.createOne(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Operation(summary = "Update one product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ProductDto updateOne(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest request) {
        return productService.updateOne(id, request);
    }

    @Operation(summary = "Delete one product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ProductDto deleteOne(@PathVariable Long id) {
        return productService.deleteOne(id);
    }
}
