package com.laplace.lapace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Spinner;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.laplace.alert.AlertActivity;
import com.laplace.download.PictureDownloadActivity;
import com.laplace.download.VideoDownloadActivity;
import com.example.lapace.R;
import com.laplace.mqttclient.MQTTActivity;
import com.laplace.notification.NotifyActivity;
import com.laplace.powercheck.JurisdictionCheckActivity;
import com.laplace.recycler.RecyclerViewActivity;
import com.laplace.recycler.ViewPager2Activity;
import com.laplace.recycler.ViewPagerActivity;

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
        findViewById(R.id.chat_page).setOnClickListener(this);
        findViewById(R.id.MQTT_Client).setOnClickListener(this);

    }

    /**
     * 弹出框
     */
    public void alertChose() {
        View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view, null);
//        ImageView imageView = dialog_view.findViewById(R.id.dialog_image);
//        Picasso.get().load("http://bpic.588ku.com/element_origin_min_pic/16/10/29/2ac8e99273bc079e40a8dc079ca11b1f.jpg").into(imageView);
        AlertDialog.Builder builder = new AlertDialog.Builder(getLayoutInflater().getContext());
        AlertDialog alertDialog = builder
                .setIcon(R.mipmap.ic_white_icon)
                .setTitle("下载图片or视频?")
                .setView(dialog_view)
                .setPositiveButton("视频", (dialogInterface, i) -> {
                    Intent intent = new Intent(dialog_view.getContext(), VideoDownloadActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("图片", (dialogInterface, i) -> {
                    Intent intent = new Intent(dialog_view.getContext(), PictureDownloadActivity.class);
                    startActivity(intent);
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(dialog_view.getContext(), "取消", Toast.LENGTH_SHORT).show();
                    }
                })
//                .setPositiveButtonIcon(getDrawable(R.drawable.positive_icon))
//                .setNegativeButtonIcon(getDrawable(R.drawable.negative_icon))
//                .setNeutralButtonIcon(getDrawable(R.drawable.neutral_icon))
                .create();
//        alertDialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        alertDialog.show();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notify_page:
                Intent intentNP = new Intent(view.getContext(), NotifyActivity.class);
                startActivity(intentNP);
                break;
            case R.id.download_page:
                alertChose();
                break;
            case R.id.jurisdiction_page:
                Intent intentJP = new Intent(view.getContext(), JurisdictionCheckActivity.class);
                startActivity(intentJP);
                break;
            case R.id.alert_page:
                Intent intentCP = new Intent(view.getContext(), AlertActivity.class);
                startActivity(intentCP);
                break;
            case R.id.recycler_page:
                Intent intentRP = new Intent(view.getContext(), RecyclerViewActivity.class);
                startActivity(intentRP);
                break;
            case R.id.view_page:
                Intent intentVP = new Intent(view.getContext(), ViewPagerActivity.class);
                startActivity(intentVP);
                break;
            case R.id.view_page2:
                Intent intentVP2 = new Intent(view.getContext(), ViewPager2Activity.class);
                startActivity(intentVP2);
                break;
            case R.id.MQTT_Client:
                Intent intentMQTT = new Intent(view.getContext(), MQTTActivity.class);
                startActivity(intentMQTT);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!canExit && keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(getLayoutInflater().getContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            canExit = true;
            handler.sendEmptyMessageDelayed(0, 3000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}