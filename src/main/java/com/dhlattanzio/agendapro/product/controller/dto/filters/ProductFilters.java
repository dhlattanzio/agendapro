package com.dhlattanzio.agendapro.product.controller.dto.filters;

import java.math.BigDecimal;

public record ProductFilters(
        String name,
        String category,
        String description,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {}
