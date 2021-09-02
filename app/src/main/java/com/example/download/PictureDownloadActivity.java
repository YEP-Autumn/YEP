package com.example.download;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lapace.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class PictureDownloadActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "YEP";
    private String url = "http://192.168.0.25:8080/MyServlet_war_exploded/one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_download);

        findViewById(R.id.start_picture_download).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_picture_download:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getPicture();
                    }
                }).start();
                break;
        }
    }


    private void getPicture(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.baidu.com").get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.toString());
            }
        });


//        new Retrofit.Builder()
//                .baseUrl("https://www.taobao.com")
//                .

    }
}