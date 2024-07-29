package com.dhlattanzio.agendapro.product.event;

import java.math.BigDecimal;

public record ProductCreatedEvent(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price
) {}
