package com.example.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lapace.R;

public class PropertyAnimationActivity extends AppCompatActivity {

    private static final String TAG = "YEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 01)
                .setDuration(2000);
        valueAnimator.addUpdateListener(valueAnimator1 -> Log.e(TAG, "valueAnimator: " + valueAnimator1.getAnimatedValue()));
        valueAnimator.start();


    }
}