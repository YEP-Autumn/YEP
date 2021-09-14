package com.example.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.lapace.R;
import com.example.socketClient.Client;
import com.example.socketClient.MsgHandler;

import org.java_websocket.client.WebSocketClient;

import java.util.List;

import lombok.Builder;

public class ChatP2PActivity extends AppCompatActivity {
    ChatHandler handler;
    WebSocketClient webSocketClient;
    RecyclerView recyclerView;
    MsgAdapter msgAdapter;
    private String TAG = "YEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_p2_pactivity);
        recyclerView = findViewById(R.id.chat_msg_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        msgAdapter = new MsgAdapter(null, getApplicationContext());
        recyclerView.setAdapter(msgAdapter);

        handler = new ChatHandler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                Log.e(TAG, "handleMessage:");
                switch (message.what) {
                    case 0x000:
                        Toast.makeText(getApplicationContext(), (String) message.obj, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        msgAdapter.setMsg((List<String>) message.obj);
                        recyclerView.setAdapter(msgAdapter);
                        break;
                }
                return true;
            }
        });
        // 开启socket连接
        startClient();
    }


    private void startClient() {
        new Thread(() -> {
            MsgHandler.setChatHandler(handler);
            Client client = new Client("2017248646");
            webSocketClient = client.getWebSocket();
            webSocketClient.connect();
            Log.e(TAG, "获得webSocketClient");
        }).start();
    }

}