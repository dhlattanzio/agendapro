package com.dhlattanzio.agendapro.product.service;

import com.dhlattanzio.agendapro.product.controller.dto.ProductDto;
import com.dhlattanzio.agendapro.product.controller.dto.filters.ProductFilters;
import com.dhlattanzio.agendapro.product.controller.dto.request.CreateProductRequest;
import com.dhlattanzio.agendapro.product.controller.dto.request.UpdateProductRequest;
import com.dhlattanzio.agendapro.shared.dto.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDto getOne(Long id);
    PageResponse<ProductDto> getAll(Pageable pageable, ProductFilters filters);
    ProductDto createOne(CreateProductRequest request);
    ProductDto updateOne(Long id, UpdateProductRequest request);
    ProductDto deleteOne(Long id);
}
