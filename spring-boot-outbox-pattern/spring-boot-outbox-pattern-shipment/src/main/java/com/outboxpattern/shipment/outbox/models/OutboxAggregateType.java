package com.outboxpattern.shipment.outbox.models;

public enum OutboxAggregateType {
    ORDER("Order"),SHIPMENT("Shipment");
    private String value;

    OutboxAggregateType(String value){
        this.value=value;
    }

    public String value(){
        return this.value;
    }
}
