package com.laplace.bean;

import java.sql.Timestamp;

public class Message {
    Timestamp timestamp;
    String message;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(Timestamp timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}
