package com.example.json;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bean.JsonArrayBean;
import com.example.bean.JsonOrdinaryBean;
import com.example.lapace.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class JsonActivity extends AppCompatActivity {

    private String TAG = "YEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        fun(findViewById(R.id.json_text_view));
    }

    private void fun(TextView textView) {

    }

}