package com.outboxpattern.order.event;

import com.fasterxml.jackson.databind.JsonNode;

public interface ExportedEvent {
    String getAggregateId();
    String getAggregateType();
    JsonNode getPayload();
    String getType();
}
