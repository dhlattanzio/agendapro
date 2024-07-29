package com.dhlattanzio.agendapro.product.controller.dto.filters;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductFilters(
        String name,
        String category,
        String description,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {}
