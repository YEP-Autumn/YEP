package com.laplace.mqttclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapace.R;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTActivity extends AppCompatActivity {

    private String TAG = "YEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqttactivity);

        TextView serverText = findViewById(R.id.MQTT_server);
        TextView portText = findViewById(R.id.port);
        TextView idText = findViewById(R.id.client_id);
        TextView usernameText = findViewById(R.id.username);
        TextView passwordText = findViewById(R.id.password);
        TextView errorText = findViewById(R.id.error_message);


        Button button = findViewById(R.id.connect_MQTT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isConnect = false;

                String server = serverText.getText().toString();
                String port = portText.getText().toString();
                String id = idText.getText().toString();
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                try {
                    isConnect = MQTTUtils.connect("tcp://" + server + ":" + port, id, username, password, true);
                } catch (MqttException e) {
                    errorText.setTextColor(getResources().getColor(R.color.red));
                    errorText.setText(e.getMessage());
                }

                if (isConnect) {
                    Toast.makeText(getLayoutInflater().getContext(), "服务器连接成功", Toast.LENGTH_SHORT).show();
                    errorText.setText("");
                    Intent intent = new Intent(getLayoutInflater().getContext(), SubscribeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getLayoutInflater().getContext(), "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}