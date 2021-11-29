package com.laplace.download;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.laplace.Adapter.AdapterDemo;
import com.example.lapace.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PictureDownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "YEP";
    //    private String url = "https://tse1-mm.cn.bing.net/th/id/R-C.06ce7237f905735df8cfcff44d6a11fa?rik=YruL7uefVPKMiQ&riu=http%3a%2f%2fpic37.nipic.com%2f20140104%2f3970232_165407697000_2.jpg&ehk=AWvGiGn1EQJjR23BP%2f9G0i21Nqr64moWF%2fjETBK%2fjvY%3d&risl=&pid=ImgRaw&r=0";
    private String url = "https://www.baidu.com/";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_download);
        findViewById(R.id.start_picture_download).setOnClickListener(this);
        listView = findViewById(R.id.picture_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(R.id.item_text);
                String s = new StringBuilder().append("我是第").append(i).append("个Item")
                        .append("  ").append("我被点击了")
                        .append("  ").append(":)").toString();
                textView.setText(s);
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_picture_download:
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    list.add(String.valueOf(i));
                }
                listView.setAdapter(new AdapterDemo(getApplicationContext(), list, Arrays.asList(R.drawable.icon)));
                break;
        }
    }

    private Integer fun() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
                .url("https://tse1-mm.cn.bing.net/th/id/R-C.06ce7237f905735df8cfcff44d6a11fa?rik=YruL7uefVPKMiQ&riu=http%3a%2f%2fpic37.nipic.com%2f20140104%2f3970232_165407697000_2.jpg&ehk=AWvGiGn1EQJjR23BP%2f9G0i21Nqr64moWF%2fjETBK%2fjvY%3d&risl=&pid=ImgRaw&r=0")
                .get();

        return null;
    }


}