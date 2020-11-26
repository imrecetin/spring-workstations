package com.outboxpattern.shipment.outbox.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.outboxpattern.shipment.event.ExportedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboxEvent implements ExportedEvent {
    String aggregateId;
    String aggregateType;
    JsonNode payload;
    String type;
}
