package com.example.socketClient;

import android.os.Message;
import android.util.Log;

import com.example.bean.Signalman;
import com.example.chat.ChatHandler;
import com.google.gson.Gson;

import org.java_websocket.handshake.ServerHandshake;

import java.util.ArrayList;
import java.util.List;

public class MsgHandler {
    private static String secWebSocketKey;
    private static Gson gson = new Gson();
    private static ChatHandler chatHandler;
//    private static Message message = Message.obtain();
    private static List<String> msgList = new ArrayList<>();
    private static String TAG = "YEP";

    public static void setChatHandler(ChatHandler chatHandler) {
        MsgHandler.chatHandler = chatHandler;
    }

    public void sendMsg(WebSocketClient webSocket, int s1, String s2) {
        Signalman Signalman = new Signalman("SEND", s1, s2, secWebSocketKey);
        String s = gson.toJson(Signalman);
        webSocket.send(s);
    }

    public static void onMessage(String s) {
        Signalman Signalman = gson.fromJson(s, Signalman.class);
        if ("WELCOME".equals(Signalman.MODE)) {
            Log.e(TAG, "WELCOME模式");
            secWebSocketKey = Signalman.secWebSocketKey;
//            System.out.println("欢迎进入");
            msgList.add("欢迎进入");
            chatHandler.sendMessage(getMessage(0x111, msgList));
            return;
        }
        if ("COMMON".equals(Signalman.MODE)) {
            Log.e(TAG, "COMMON模式");
            String msg = Signalman.msg == null ? "" : Signalman.msg;
//            System.out.println(msg);
            try {
                msgList.add(msg);
                chatHandler.sendMessage(getMessage(0x222, msgList));
            }catch (Exception e){
                Log.e(TAG, "Exception ", e);
            }
            return;
        }
        if ("CLOSE".equals(Signalman.MODE)) {
//            System.out.println("连接即将关闭");
            return;
        }
    }


    static void close(int i, String s, boolean b) {
        Log.e(TAG, "连接关闭" + " " + i + " " + s + " " + b);
    }

    public static void onOpen(ServerHandshake serverHandshake) {
        chatHandler.sendMessage(getMessage(0x000, "服务开启"));
    }

    public static Message getMessage(int what, Object obj) {
        Message message = new Message();
        message.what = what;
        message.obj = obj;
        return message;
    }

}
