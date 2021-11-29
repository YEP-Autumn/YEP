package com.laplace.mqttclient;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface TopicHandler {
    void received(String topic, MqttMessage message);
}
