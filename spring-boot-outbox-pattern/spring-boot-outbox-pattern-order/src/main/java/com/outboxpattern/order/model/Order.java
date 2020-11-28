package com.outboxpattern.order.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.outboxpattern.order.outbox.models.OutboxAggregateType;
import com.outboxpattern.order.outbox.models.OutboxEvent;
import com.outboxpattern.order.outbox.models.OutboxOrderEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order",schema="ordersch")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public OutboxEvent createOrderEvent() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(this, JsonNode.class);
        return OutboxEvent.builder()
                .aggregateId(this.getId().toString())
                .aggregateType(OutboxAggregateType.ORDER.value())
                .payload(jsonNode).type(OutboxOrderEventType.ORDER_CREATED.value()).build();
    }
}
