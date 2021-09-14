package com.example.socketClient;
import android.util.Log;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Client {
    private final String TAG = "YEP";
    HashMap<String, String> map = new HashMap<>();
    WebSocketClient webSocket = null;

    public Client(String userId) {
        map.put("userId", userId);
    }

    public synchronized WebSocketClient getWebSocket() {
        if (webSocket == null) {
            try {
                webSocket = new WebSocketClient(new URI("ws://192.168.0.217:8788"), map);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return webSocket;
    }
}
