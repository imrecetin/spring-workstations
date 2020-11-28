package com.outboxpattern.shipment.outbox;

import com.outboxpattern.shipment.outbox.models.OutBox;
import com.outboxpattern.shipment.outbox.models.OutboxEvent;
import com.outboxpattern.shipment.outbox.repository.OutBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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
        final OutBox outbox = OutBox.builder().uuid(uuid).build();

        log.info("Handling event : {}.", outbox);

        outBoxRepository.save(outbox);
        /*
         * Delete the event once written, so that the outbox doesn't grow.
         * The CDC eventing polls the database log entry and not the table in the database.
         */
        outBoxRepository.delete(outbox);
    }
}
