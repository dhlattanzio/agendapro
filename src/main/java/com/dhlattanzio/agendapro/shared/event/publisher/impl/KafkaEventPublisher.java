package com.dhlattanzio.agendapro.shared.event.publisher.impl;

import com.dhlattanzio.agendapro.shared.event.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaEventPublisher implements EventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(String topic, Object event) {
        kafkaTemplate.send(topic, event);
    }
}
