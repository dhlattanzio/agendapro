package com.dhlattanzio.agendapro.product.event;

public record ProductDeletedEvent(
        Long id,
        String category
) {}
