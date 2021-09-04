package com.example.recycler;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.Adapter.RecyclerAdapterDemo;
import com.example.lapace.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        findViewById(R.id.recycler_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.recycler_button:
                buttonFun();
        }
    }

    private void buttonFun() {
        List<String> data = new ArrayList<>();
        List<Integer> imgList = Arrays.asList(R.drawable.icon);
        for (int i = 0; i < 50; i++) {
            data.add("Item " + i + " :)");
        }
        RecyclerAdapterDemo recyclerAdapterDemo = new RecyclerAdapterDemo(getApplicationContext(), data, imgList);
        recyclerAdapterDemo.setOnRecyclerItemClickListener(new RecyclerAdapterDemo.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(TextView textView, int i) {
                textView.setText("我是第" + i +"个Item,我被点击了 :)");
            }
        });
        recyclerView.setAdapter(recyclerAdapterDemo);
    }

}