package com.laplace.mqttclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapace.R;
import com.laplace.bean.TopicStr;
import com.laplace.download.PictureDownloadActivity;
import com.laplace.download.VideoDownloadActivity;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubscribeActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    TopicAdapter adapter;

    TopicsMessageManager manager;

    EditText topicText;
    TextView logText;

    Handler handler;
    private final String TAG = "YEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        topicText = findViewById(R.id.subscribe_topic);
        logText = findViewById(R.id.error_log_subscribe);

        recyclerView = findViewById(R.id.topics);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter = new TopicAdapter(getLayoutInflater().getContext(), new ArrayList<>());
        adapter.setOnClickListener(new TopicOnClickListener() {


            @Override
            public void setMessageOnclickListener(String topic, List<com.laplace.bean.Message> messageList) {
                View view = getLayoutInflater().inflate(R.layout.empty_recycler, null);
                RecyclerView recyclerView = view.findViewById(R.id.message_recycler);
                recyclerView.setAdapter(new MessageDetailAdapter(getLayoutInflater().getContext(), messageList));
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                AlertDialog alertDialog = new AlertDialog.Builder(getLayoutInflater().getContext()).setView(view).create();
                alertDialog.show();
            }

            @Override
            public void setUnSubscribe(String topic, int position) {
                MQTTUtils.unSubscribe(topic);
                Toast.makeText(getLayoutInflater().getContext(), "取消订阅成功", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        findViewById(R.id.subscribe).setOnClickListener(this);
        manager = new TopicsMessageManager();
        handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if (message.what == 0x00) {
                    adapter.notifyDataSetChanged();
                }
                if (message.what == 0x11) {
                    if (message.obj != null) {
                        Toast.makeText(getLayoutInflater().getContext(), message.obj.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.subscribe) {
                if ("".equals(topicText.getText().toString())) {
                    handler.sendMessage(getMessage(0x11, "订阅主题不能为空"));
                    return;
                }
                View dialog_view = getLayoutInflater().inflate(R.layout.topic_attach_info_dialog, null);
                EditText alisa = dialog_view.findViewById(R.id.topic_alias);
                EditText qos = dialog_view.findViewById(R.id.qos_select);
                View determine = dialog_view.findViewById(R.id.determine);
                AlertDialog dialog = new AlertDialog.Builder(getLayoutInflater().getContext()).setView(dialog_view).create();
                determine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (qos == null || alisa == null) return;
                        int qosN = 0;
                        if (!"".equals(qos.getText().toString())) {
                            qosN = Integer.parseInt(qos.getText().toString());
                        }
                        qosN = Math.max(qosN, 0);
                        qosN = Math.min(qosN, 2);

                        adapter.add(new TopicStr(alisa.getText().toString(), topicText.getText().toString(), qosN, ""));
                        handler.sendEmptyMessage(0x00);
                        try {
                            MQTTUtils.subscribe(topicText.getText().toString(), qosN, new TopicHandler() {
                                @Override
                                public void received(String topic, MqttMessage message) {
                                    adapter.updateTopic(new TopicStr(topic, new String(message.getPayload())));
                                    handler.sendEmptyMessage(0x00);
                                    handler.sendMessage(getMessage(0x11, "新消息"));
                                }
                            });
                            handler.sendMessage(getMessage(0x11, "订阅成功"));
                            topicText.setText("");
                        } catch (MqttException e) {
                            logText.setTextColor(getResources().getColor(R.color.red));
                            logText.setText(e.getMessage());
                        }
                        dialog.cancel();
                    }
                });
                dialog.show();
                return;
            }
        } catch (Exception e) {
            Log.e(TAG, "error", e);
        }

    }


    public Message getMessage(int what, String message) {
        Message ms = new Message();
        ms.what = what;
        ms.obj = message;
        return ms;
    }
}