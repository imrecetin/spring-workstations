package com.outboxpattern.order.outbox.models;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outbox")
public class OutBox {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "aggregate_id")
    private String aggregateId;

    @Column(name = "aggregate_type")
    private String aggregatetype;

    @Column(name = "type")
    private String type;

    @JsonRawValue
    @Column(name = "payload")
    private String payload;

    @Column(name = "created_on")
    private Date createdOn;

    @PostLoad
    public void afterLoad() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String  payload = mapper.readValue(this.getPayload(), String.class);
        this.payload=payload;
    }

    public OutBox outBoxEvent(OutboxEvent event){
        this.aggregateId=event.getAggregateId();
        this.aggregatetype=event.getAggregateType();
        this.payload=event.getPayload().toPrettyString();
        this.type=event.getType();
        return this;
    }
}
