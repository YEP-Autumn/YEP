package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.Adapter.RecyclerAdapterDemo;
import com.example.Adapter.ViewPager2AdapterDemo;
import com.example.lapace.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        List<Drawable> list = new ArrayList<>();
        list.add(getDrawable(R.drawable.p1));
        list.add(getDrawable(R.drawable.p2));
        list.add(getDrawable(R.drawable.p3));
        list.add(getDrawable(R.drawable.p4));
        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);
        viewPager2.setAdapter(new ViewPager2AdapterDemo(getApplicationContext(),list));
    }
}