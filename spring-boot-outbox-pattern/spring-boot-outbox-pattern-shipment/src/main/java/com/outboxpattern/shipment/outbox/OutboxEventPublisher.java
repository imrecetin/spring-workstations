package com.outboxpattern.shipment.outbox;

import com.outboxpattern.shipment.outbox.models.OutboxEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class OutboxEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void fire(OutboxEvent outboxEvent) {
        this.publisher.publishEvent(outboxEvent);
    }
}
