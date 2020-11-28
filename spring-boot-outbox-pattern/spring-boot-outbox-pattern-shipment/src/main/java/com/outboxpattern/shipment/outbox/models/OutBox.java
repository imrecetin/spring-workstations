package com.outboxpattern.shipment.outbox.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outbox",schema="shipmentsch")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
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

    @Type(type = "jsonb")
    @Column(name = "payload")
    private String payload;

    @Column(name = "created_on")
    private Date createdOn;

    public OutBox outBoxEvent(OutboxEvent event){
        this.aggregateId=event.getAggregateId();
        this.aggregatetype=event.getAggregateType();
        this.payload=event.getPayload().toPrettyString();
        this.type=event.getType();
        return this;
    }
}
