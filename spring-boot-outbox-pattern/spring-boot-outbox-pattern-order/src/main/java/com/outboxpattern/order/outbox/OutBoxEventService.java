package com.outboxpattern.order.outbox;

import com.outboxpattern.order.outbox.repository.OutBoxRepository;
import com.outboxpattern.order.outbox.models.OutBox;
import com.outboxpattern.order.outbox.models.OutboxEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OutBoxEventService {

    private final OutBoxRepository outBoxRepository;

    @Autowired
    public OutBoxEventService(OutBoxRepository outBoxRepository) {
        this.outBoxRepository = outBoxRepository;
    }

    @EventListener
    public void handleOutboxEvent(OutboxEvent event) {
        UUID uuid = UUID.randomUUID();
        final OutBox outbox = OutBox.builder().uuid(uuid).createdOn(new Date()).build().outBoxEvent(event);
        log.info("Handling event : {}.", outbox);
        outBoxRepository.save(outbox);
        /*
         * Delete the event once written, so that the outbox doesn't grow.
         * The CDC eventing polls the database log entry and not the table in the database.
         */
        outBoxRepository.delete(outbox);
    }
}
