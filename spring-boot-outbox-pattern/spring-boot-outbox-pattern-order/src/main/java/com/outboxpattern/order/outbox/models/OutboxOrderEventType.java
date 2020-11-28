package com.outboxpattern.order.outbox.models;

public enum OutboxOrderEventType {
    ORDER_CREATED("OrderCreated"),
    ORDER_UPDATED("OrderUpdated"),
    ORDER_DELETED("OrderDeleted");
    private String value;
    OutboxOrderEventType(String value){
        this.value=value;
    }

    public String value(){
        return this.value;
    }

}
