package com.example.socketClient;


import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {

    private String TAG = "YEP";

    public WebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public WebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        super(serverUri, protocolDraft, httpHeaders);
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        MsgHandler.onOpen(serverHandshake);
    }


    @Override
    public void onMessage(String s) {
        Log.e(TAG, "onMessage: " + s);
        MsgHandler.onMessage(s);
    }


    @Override
    public void onClose(int i, String s, boolean b) {
        MsgHandler.close(i, s, b);
    }


    @Override
    public void onError(Exception e) {

    }


}