package com.outboxpattern.order.outbox.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.outboxpattern.order.event.ExportedEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutboxEvent implements ExportedEvent {
    String aggregateId;
    String aggregateType;
    JsonNode payload;
    String type;
}
