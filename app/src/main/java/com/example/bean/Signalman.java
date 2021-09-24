package com.example.bean;

public class Signalman {

    /**
     * 消息模式
     * WELCOME: 欢迎模式，用户第一次上线时发送
     * COMMON: 普通模式，给指定用户发送未经加密的消息
     * CLOSE: 通知用户连接即将关闭
     */
    public String MODE;

    public String msg;

    public String secWebSocketKey;

    /**
     * 用户的登录名
     */
    public int userId;

    /**
     * 用户发送的消息
     */

    /**
     * 用户自己的秘钥，用户消息发送失败时的反馈
     */
    public String mKey;


    public Signalman(String MODE, String msg, String secWebSocketKey) {
        this.MODE = MODE;
        this.msg = msg;
        this.secWebSocketKey = secWebSocketKey;
    }

    public Signalman(String MODE, String msg) {
        this.MODE = MODE;
        this.msg = msg;
    }


    public Signalman(String MODE, int userId, String msg, String mKey) {
        this.MODE = MODE;
        this.userId = userId;
        this.msg = msg;
        this.mKey = mKey;
    }

    public Signalman(String MODE, int userId, String msg) {
        this.MODE = MODE;
        this.userId = userId;
        this.msg = msg;
    }
}
