package com.laplace.bean;

import lombok.Data;

public class TopicStr {

    String name;

    String topic;

    int qoS;

    String message;

    public TopicStr(String name, String topic, int qoS, String message) {
        this.name = name;
        this.topic = topic;
        this.qoS = qoS;
        this.message = message;
    }

    public TopicStr(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQoS() {
        return qoS;
    }

    public void setQoS(int qoS) {
        this.qoS = qoS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
