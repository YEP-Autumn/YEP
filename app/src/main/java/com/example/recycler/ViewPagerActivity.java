package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.Adapter.ViewPagerAdapterDemo;
import com.example.lapace.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        LayoutInflater inflater = getLayoutInflater().from(this);
        List<View> list = new ArrayList<>();
        list.add(inflater.inflate(R.layout.view_pager_one,null));
        list.add(inflater.inflate(R.layout.view_pager_two,null));
        list.add(inflater.inflate(R.layout.view_pager_three,null));
        list.add(inflater.inflate(R.layout.view_pager_four,null));

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapterDemo(list));

    }
}