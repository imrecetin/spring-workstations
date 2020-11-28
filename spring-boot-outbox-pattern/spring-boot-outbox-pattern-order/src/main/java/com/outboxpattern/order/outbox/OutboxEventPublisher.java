package com.outboxpattern.order.outbox;

import com.outboxpattern.order.outbox.models.OutboxEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OutboxEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void fire(OutboxEvent outboxEvent) {
        this.publisher.publishEvent(outboxEvent);
    }
}
