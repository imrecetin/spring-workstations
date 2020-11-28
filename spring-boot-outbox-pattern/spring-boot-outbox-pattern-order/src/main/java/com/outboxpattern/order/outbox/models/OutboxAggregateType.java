package com.outboxpattern.order.outbox.models;

public enum OutboxAggregateType {
    ORDER("Order");
    private String value;

    OutboxAggregateType(String value){
        this.value=value;
    }

    public String value(){
        return this.value;
    }
}
