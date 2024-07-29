package com.dhlattanzio.agendapro.product.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price
) {}
