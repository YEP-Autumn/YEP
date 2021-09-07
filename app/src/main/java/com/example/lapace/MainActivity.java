package com.example.lapace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alert.AlertActivity;
import com.example.contact.ContactsActivity;
import com.example.download.PictureDownloadActivity;
import com.example.download.VideoDownloadActivity;
import com.example.notification.NotifyActivity;
import com.example.powercheck.JurisdictionCheckActivity;
import com.example.recycler.RecyclerViewActivity;
import com.example.recycler.ViewPager2Activity;
import com.example.recycler.ViewPagerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "YEP";

    private boolean canExit = false;
    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            canExit = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  隐藏标题栏
        //  getSupportActionBar().hide();
        findViewById(R.id.notify_page).setOnClickListener(this);
        findViewById(R.id.download_page).setOnClickListener(this);
        findViewById(R.id.jurisdiction_page).setOnClickListener(this);
        findViewById(R.id.alert_page).setOnClickListener(this);
        findViewById(R.id.recycler_page).setOnClickListener(this);
        findViewById(R.id.view_page).setOnClickListener(this);
        findViewById(R.id.view_page2).setOnClickListener(this);

    }

    /**
     * 弹出框
     */
    public void alertChose() {
        View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_white_icon)
                .setTitle("下载图片or视频?")
                .setView(dialog_view)
                .setPositiveButton("视频", (dialogInterface, i) -> {
                    Intent intent = new Intent(MainActivity.this, VideoDownloadActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("图片", (dialogInterface, i) -> {
                    Intent intent = new Intent(MainActivity.this, PictureDownloadActivity.class);
                    startActivity(intent);
                })
                .setNeutralButton("取消", (dialog, which) -> {
                    Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
                })
//                .setPositiveButtonIcon(getDrawable(R.drawable.positive_icon))
//                .setNegativeButtonIcon(getDrawable(R.drawable.negative_icon))
//                .setNeutralButtonIcon(getDrawable(R.drawable.neutral_icon))
                .create()
                .show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notify_page:
                Intent intentNP = new Intent(MainActivity.this, NotifyActivity.class);
                startActivity(intentNP);
                break;
            case R.id.download_page:
                alertChose();
                break;
            case R.id.jurisdiction_page:
                Intent intentJP = new Intent(MainActivity.this, JurisdictionCheckActivity.class);
                startActivity(intentJP);
                break;
            case R.id.alert_page:
                Intent intentCP = new Intent(MainActivity.this, AlertActivity.class);
                startActivity(intentCP);
                break;
            case R.id.recycler_page:
                Intent intentRP = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intentRP);
                break;
            case R.id.view_page:
                Intent intentVP = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intentVP);
                break;
            case R.id.view_page2:
                Intent intentVP2 = new Intent(MainActivity.this, ViewPager2Activity.class);
                startActivity(intentVP2);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!canExit && keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            canExit = true;
            handler.sendEmptyMessageDelayed(0, 3000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}