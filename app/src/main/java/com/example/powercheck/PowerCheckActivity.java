package com.example.powercheck;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.lapace.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerCheckActivity extends AppCompatActivity {

    private static final String TAG = "YEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_check);


        //读写权限验证的click事件
        findViewById(R.id.read_write).setOnClickListener(view -> {
            String[] checkList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
            checkSetJurisdiction(checkList);
        });


        //全局弹窗权限的click事件
        /**
         * 判断是否有全局弹窗的权限，如果没有则跳转到设置界面。
         */
        findViewById(R.id.alert_global).setOnClickListener(view -> {
            if (! Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "浮窗权限,请前往设置", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW} ,200);
        });
    }

    /**
     * 判断权限是否拥有，并申请没有的权限
     * @param JurisdictionList
     */
    private void checkSetJurisdiction(String [] JurisdictionList) {
        //判断那些权限没有
        List<String> list = new ArrayList<>();
        for (String jd:JurisdictionList) {
            if(PackageManager.PERMISSION_GRANTED!= ActivityCompat.checkSelfPermission(getApplicationContext(),jd)){
                list.add(jd);
            }else {
                Toast.makeText(this, "该权限已授予", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "该权限已授予: "+jd);
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
        /*
        requestCode: ActivityCompat.requestPermissions中设置的requestCode
        permissions: 权限名称
        grantResults: 【拒绝】-1 【允许】0
         */
        switch(requestCode){
            case 100:
                Log.e(TAG, "读写权限的申请" );
                break;
            case 200:
                Log.e(TAG, "全局弹窗的权限验证" );
        }
        if(grantResults[0] == 0){
            Toast.makeText(this,"感谢您的信任", Toast.LENGTH_SHORT).show();
        }else{

        }
        Log.e(TAG, "requestCode: " + requestCode);
        Log.e(TAG, "grantResults: " + Arrays.toString(grantResults));
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        //  第一次请求该权限，返回false。
        //  请求过该权限并被用户拒绝，返回true。
        //  请求过该权限，但用户拒绝的时候勾选不再提醒，返回false。
        return super.shouldShowRequestPermissionRationale(permission);
    }




}