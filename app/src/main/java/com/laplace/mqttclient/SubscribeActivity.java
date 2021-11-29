package com.laplace.mqttclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapace.R;
import com.laplace.bean.TopicStr;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public class SubscribeActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    TopicAdapter adapter;

    TopicsMessageManager manager;

    TextView topicText;
    TextView logText;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        topicText = findViewById(R.id.subscribe_topic);
        logText = findViewById(R.id.error_log_subscribe);

        recyclerView = findViewById(R.id.topics);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter = new TopicAdapter(getLayoutInflater().getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        findViewById(R.id.subscribe).setOnClickListener(this);
        manager = new TopicsMessageManager();
        handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if (message.what == 0x11) {
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.subscribe) {
            adapter.topics.add(new TopicStr("", topicText.getText().toString(), 2, ""));
            adapter.notifyDataSetChanged();
            try {
                MQTTUtils.subscribe(topicText.getText().toString(), 2, new TopicHandler() {
                    @Override
                    public void received(String topic, MqttMessage message) {
                        adapter.updateTopic(new TopicStr(topic, new String(message.getPayload())));
                        handler.sendEmptyMessage(0x00);
                    }
                });
                Toast.makeText(getLayoutInflater().getContext(), "订阅成功", Toast.LENGTH_SHORT).show();
            } catch (MqttException e) {
                logText.setTextColor(getResources().getColor(R.color.red));
                logText.setText(e.getMessage());
            }
        }
    }
}