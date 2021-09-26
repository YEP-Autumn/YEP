package com.example.socketClient;
import android.util.Log;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Client {
    private final String TAG = "YEP";
    HashMap<String, String> map = new HashMap<>();
    WebSocketClient webSocket = null;

    public Client(String userId,String friendId) {
        map.put("userId", userId);
        map.put("friendId", friendId);
    }

    public synchronized WebSocketClient getWebSocket() {
        if (webSocket == null) {
            try {
                webSocket = new WebSocketClient(new URI("ws://192.168.1.4:8788"), map);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return webSocket;
    }
}
