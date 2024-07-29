package com.dhlattanzio.agendapro.product.event;

public record ProductUpdatedEvent(
        Long id,
        String category,
        String previousCategory
) {}
