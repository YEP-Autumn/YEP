package com.laplace.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.lapace.R;


public class NotifyActivity extends AppCompatActivity {

    private String TAG = "YEP";
    private  YEPNotification notification;
    private  YEPNotification notificationTwo;
    private  YEPNotification notificationThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        Button buttonOne = findViewById(R.id.btn_one);
        Button button_two = findViewById(R.id.btn_two);
        Button button_three = findViewById(R.id.btn_three);
        buttonOne.setOnClickListener(view -> {
            Toast.makeText(getBaseContext(), "默认通知", Toast.LENGTH_SHORT).show();
            if(notification == null) notification = new YEPNotification(getBaseContext());
            notification.startN();
        });
        button_two.setOnClickListener(view -> {
            Toast.makeText(getBaseContext(), "设置大图片的通知", Toast.LENGTH_SHORT).show();
            if(notificationTwo == null) notificationTwo = new YEPNotification(getBaseContext());
            notificationTwo.setBitmapResource(BitmapFactory.decodeResource(getResources(),R.drawable.illust));
            notificationTwo.setTitle("图片通知");
            notificationTwo.setText("这是一个图片通知");
            notificationTwo.startN();
        });

        button_three.setOnClickListener(view -> {
            Toast.makeText(getBaseContext(), "设置大文字的通知", Toast.LENGTH_SHORT).show();
            if(notificationThree == null) notificationThree = new YEPNotification(getBaseContext());
            notificationThree.setTitle("文字通知的标题");
            notificationThree.setText("这是小文本");
            notificationThree.setBigText("这是预览文字");
            notificationThree.startN();
        });
    }
}