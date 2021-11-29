package com.laplace.alert;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lapace.R;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        findViewById(R.id.alert_style_one).setOnClickListener(this);
        findViewById(R.id.alert_style_two).setOnClickListener(this);
        findViewById(R.id.alert_style_three).setOnClickListener(this);
        findViewById(R.id.alert_style_four).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.alert_style_one:
                funOne();
                break;
            case R.id.alert_style_two:
                funTwo();
                break;
            case R.id.alert_style_three:
                funThree();
                break;
            case R.id.alert_style_four:
                funFour();
                break;
        }

    }


    private void funOne(){
    }

    private void funTwo() {
    }

    private void funThree() {
    }

    private void funFour() {
    }


}