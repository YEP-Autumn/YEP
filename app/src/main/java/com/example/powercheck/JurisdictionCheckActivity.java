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

public class JurisdictionCheckActivity extends AppCompatActivity {

    private static final String TAG = "YEP";
    private JurisdictionCheckHandler jurisdictionCheckHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_check);

        jurisdictionCheckHandler = new JurisdictionCheckHandler(getApplicationContext(),this);
        //读写权限验证的click事件
        findViewById(R.id.read_write).setOnClickListener(view -> {
            String[] checkList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
            String[] notOwnJsd = jurisdictionCheckHandler.checkSetJurisdiction(checkList);
            jurisdictionCheckHandler.getJurisdiction(notOwnJsd,100);
            boolean bResult = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(bResult) Toast.makeText(this, "读写权限被拒绝(why we need this?)", Toast.LENGTH_SHORT).show();
        });

        /**
         * 判断是否有全局弹窗的权限，如果没有则跳转到设置界面。
         */
        findViewById(R.id.alert_global).setOnClickListener(view -> {
            String[] checkList = new String[]{
                    Manifest.permission.SYSTEM_ALERT_WINDOW
            };
            String[] notOwnJsd = jurisdictionCheckHandler.checkSetJurisdiction(checkList);
            jurisdictionCheckHandler.getJurisdiction(notOwnJsd,200);
            boolean bResult = shouldShowRequestPermissionRationale(Manifest.permission.SYSTEM_ALERT_WINDOW);
            if(bResult) Toast.makeText(this, "全局弹窗权限被拒绝(why we need this?)", Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent,10);

        });

        findViewById(R.id.write_contact).setOnClickListener(view->{
            String[] checkList = new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS
            };
            String[] notOwnJsd = jurisdictionCheckHandler.checkSetJurisdiction(checkList);
            jurisdictionCheckHandler.getJurisdiction(notOwnJsd,300);
            boolean bResult = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(bResult) Toast.makeText(this, "读写联系人权限被拒绝(why we need this?)", Toast.LENGTH_SHORT).show();
        });


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
                Log.e(TAG, "存储读写权限" );
                break;
            case 200:
                Log.e(TAG, "全局弹窗权限" );
                break;
            case 300:
                Log.e(TAG, "读写联系人权限");
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