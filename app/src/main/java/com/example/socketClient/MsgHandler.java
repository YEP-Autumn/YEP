package com.example.socketClient;

import android.os.Message;
import android.util.Log;

import com.example.bean.Chat;
import com.example.bean.Signalman;
import com.example.chat.ChatHandler;
import com.example.encrypt.A;
import com.example.encrypt.AHelper;
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
        Signalman signalman = gson.fromJson(s, Signalman.class);
        if ("WELCOME".equals(signalman.MODE)) {
            Log.e(TAG, "WELCOME模式");
            secWebSocketKey = signalman.secWebSocketKey;
//            System.out.println("欢迎进入");
            msgList.add("欢迎进入");
            if (signalman.messages.size() == 0) {
                msgList.add("无最新好友消息");
                chatHandler.sendMessage(getMessage(0x111, msgList));
                return;
            }

            for (Chat message : signalman.messages) {
                msgList.add(AHelper.toContent("YEPaAutumnAutumn",message.getMsg()));
            }
            chatHandler.sendMessage(getMessage(0x111, msgList));
            return;
        }
        if ("COMMON".equals(signalman.MODE)) {
            Log.e(TAG, "COMMON模式");
            String msg = signalman.msg == null ? "YEPaAutumnAutumn" : signalman.msg;
            try {
                msgList.add(msg);
                chatHandler.sendMessage(getMessage(0x222, msgList));
            } catch (Exception e) {
                Log.e(TAG, "Exception ", e);
            }
            return;
        }
        if ("SIGN".equals(signalman.MODE)) {
            Log.e(TAG, "SIGN模式");
            String msg = signalman.msg == null ? "" : signalman.msg;

            try {
                msgList.add(AHelper.toContent("YEPaAutumnAutumn", msg));
                chatHandler.sendMessage(getMessage(0x222, msgList));
            } catch (Exception e) {
                Log.e(TAG, "Exception ", e);
            }
            return;
        }
        if ("CLOSE".equals(signalman.MODE)) {
//            System.out.println("连接即将关闭");
            return;
        }
    }


    static void close(int i, String s, boolean b) {
        Log.e(TAG, "连接关闭" + " " + i + " " + s + " " + b);
        msgList.clear();
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
