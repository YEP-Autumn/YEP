package com.example.powercheck;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.lapace.R;

import java.util.ArrayList;
import java.util.List;

public class PowerCheckActivity extends AppCompatActivity {

    private static final String TAG = "YEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_check);

        Button button = findViewById(R.id.read_write);
        button.setOnClickListener(view -> {
            getPower();
        });
    }

    private void getPower() {
        String[] checkList = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        //判断那些权限没有
        List<String> list = new ArrayList<>();
        for (String needPower:checkList) {
            if(PackageManager.PERMISSION_GRANTED!= ActivityCompat.checkSelfPermission(getApplicationContext(),needPower)){
                list.add(needPower);
            }
        }

        //申请权限
        if(list.size() > 0){
            String[] strings = list.toArray(new String[list.size()]);
            ActivityCompat.requestPermissions(this,strings,100);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TAG, "onRequestPermissionsResult: " + String.valueOf(requestCode) );
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        //  第一次请求该权限，返回false。
        //  请求过该权限并被用户拒绝，返回true。
        //  请求过该权限，但用户拒绝的时候勾选不再提醒，返回false。
        return super.shouldShowRequestPermissionRationale(permission);
    }




}