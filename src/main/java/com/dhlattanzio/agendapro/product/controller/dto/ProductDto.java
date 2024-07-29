package com.dhlattanzio.agendapro.product.controller.dto;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price
) {}
