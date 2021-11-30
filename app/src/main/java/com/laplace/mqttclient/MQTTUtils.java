package com.laplace.mqttclient;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class MQTTUtils {

    private static String TAG = "YEP";

    public static MqttClient client = null;

    public static void connect(String server, String clientId, boolean isCleanSession) throws MqttException {
        client = new MqttClient(server, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(isCleanSession);
        options.setKeepAliveInterval(60);
        client.connect(options);
    }

    public static boolean connect(String server, String clientId, String username, String password, boolean isCleanSession) throws MqttException {
        client = new MqttClient(server, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(isCleanSession);
        options.setKeepAliveInterval(60);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        client.setManualAcks(false);
        client.connect(options);
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.e(TAG, "connectionLost: ", cause.getCause());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.e(TAG, "messageArrived: " + topic);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                try {
                    Log.e(TAG, "deliveryComplete: " + token.getMessage());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
        return client.isConnected();
    }

    static int count = 0;

    public static void publishTopic(String topic, String payload, int qoS, boolean retain) throws MqttException {
        MqttMessage message = new MqttMessage();
        message.setId(count++);
        message.setPayload(payload.getBytes(StandardCharsets.UTF_8));
        message.setQos(qoS);
        message.setRetained(retain);
        client.publish("hello", message);
    }

    public static void subscribe(String topic, int qoS, TopicHandler handler) throws MqttException {
        client.subscribe(topic, qoS, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws MqttException {
                handler.received(topic, message);
            }
        });
    }

    public static void unSubscribe(String topic)  {
        try {
            client.unsubscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
