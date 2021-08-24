package com.example.download;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.http.getH;
import com.example.lapace.R;

import java.io.IOException;

public class PictureDownloadActivity extends AppCompatActivity {

    private static final String TAG = "YEP";
    private String url = "http://192.168.0.25:8080/MyServlet_war_exploded/one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_download);

        Button button = findViewById(R.id.start_download);
        button.setOnClickListener(view -> new Thread(() -> {
            String run;
            try {
                run = getH.run(url);
                Log.e(TAG, run);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start());

        findViewById(R.id.start_post).setOnClickListener(view -> new Thread(() -> {
            getH.fun(url);
        }).start());

    }
}