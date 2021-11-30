package com.laplace.mqttclient;

import com.laplace.bean.Message;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public interface TopicOnClickListener {
    void setMessageOnclickListener(String topic, List<Message> messageList);

    void setUnSubscribe(String topic, int position);

}
