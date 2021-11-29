package com.laplace.powercheck;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JurisdictionCheckHandler {

    private static final String TAG = "YEP";
    private String [] jurisdictionList;
    private Context  context;
    private Activity activity;


    public JurisdictionCheckHandler(Context context,Activity activity) {

        this.context = context;
        this.activity = activity;

    }


    /**
     * 判断权限是否拥有，返回没有拥有的权限
     * @param jurisdictionList
     */
    public String[] checkSetJurisdiction(String[] jurisdictionList) {
        //判断那些权限没有
        List<String> list = new ArrayList<>();
        for (String jd:jurisdictionList) {
            if(PackageManager.PERMISSION_GRANTED!= ActivityCompat.checkSelfPermission(context,jd)){
                list.add(jd);
            }
        }
        Log.e(TAG, "list " + list.toArray() );
        return list.toArray(new String[list.size()]);
    }

    public void getJurisdiction(String[] jurisdictionList,int requestCode){
        if(jurisdictionList.length != 0 & activity != null) {
            ActivityCompat.requestPermissions(activity, jurisdictionList, requestCode);
        }
    }
}
