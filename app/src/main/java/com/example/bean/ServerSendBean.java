package com.example.bean;

public class ServerSendBean {

    /**
     * 消息模式
     * WELCOME: 欢迎模式，用户第一次上线时发送
     * COMMON: 普通模式，给指定用户发送未经加密的消息
     * CLOSE: 通知用户连接即将关闭
     */
    public String MODE;

    public String msg;

    public String secWebSocketKey;

    public ServerSendBean(String MODE, String msg, String secWebSocketKey) {
        this.MODE = MODE;
        this.msg = msg;
        this.secWebSocketKey = secWebSocketKey;
    }

    public ServerSendBean(String MODE, String msg) {
        this.MODE = MODE;
        this.msg = msg;
    }
}
