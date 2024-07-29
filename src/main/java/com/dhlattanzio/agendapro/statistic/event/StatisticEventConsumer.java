package com.dhlattanzio.agendapro.statistic.event;


import com.dhlattanzio.agendapro.product.constant.ProductTopic;
import com.dhlattanzio.agendapro.product.event.ProductCreatedEvent;
import com.dhlattanzio.agendapro.product.event.ProductDeletedEvent;
import com.dhlattanzio.agendapro.product.event.ProductUpdatedEvent;
import com.dhlattanzio.agendapro.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StatisticEventConsumer {
    private final StatisticService statisticService;

    @KafkaListener(topics = ProductTopic.CREATED)
    public void consumeCreated(ProductCreatedEvent event) {
        statisticService.incrementCounter(event.category());
    }

    @KafkaListener(topics = ProductTopic.UPDATED)
    public void consumeUpdated(ProductUpdatedEvent event) {
        statisticService.incrementAndDecrementCounter(event.category(), event.previousCategory());
    }

    @KafkaListener(topics = ProductTopic.DELETED)
    public void consumeDeleted(ProductDeletedEvent event) {
        statisticService.decrementCounter(event.category());
    }
}
