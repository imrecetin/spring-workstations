package com.redis.service.dto;

import java.io.Serializable;

public class ResponseAUpdate implements Serializable {

    private String newValue;

    public ResponseAUpdate() {
        this.newValue = null;
    }

    public ResponseAUpdate(final String newValue) {
        this.newValue = newValue;
    }

    public void setNewValue(final String newValue) {
        this.newValue = newValue;
    }

    public String getNewValue() {
        return newValue;
    }
}
