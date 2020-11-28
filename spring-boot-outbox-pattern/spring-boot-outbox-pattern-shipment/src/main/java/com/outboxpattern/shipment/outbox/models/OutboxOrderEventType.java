package com.outboxpattern.shipment.outbox.models;

public enum OutboxOrderEventType {
    ORDER_CREATED("OrderCreated"),
    ORDER_UPDATED("OrderUpdated"),
    ORDER_DELETED("OrderDeleted"),
    SHIPMENT_CREATED("ShipmentCreated"),
    SHIPMENT_UPDATED("ShipmentUpdated"),
    SHIPMENT_DELETED("ShipmentDeleted");
    private String value;
    OutboxOrderEventType(String value){
        this.value=value;
    }

    public String value(){
        return this.value;
    }

}
