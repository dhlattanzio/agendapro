package com.dhlattanzio.agendapro.shared.event.publisher;

public interface EventPublisher {
    void publish(String topic, Object event);
}
