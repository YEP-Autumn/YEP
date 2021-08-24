package com.example.yep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.download.PictureDownloadActivity;
import com.example.download.VideoDownloadActivity;
import com.example.notification.NotifyActivity;
import com.example.powercheck.PowerCheckActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonNotify = findViewById(R.id.notify_page);
        Button buttonDownload = findViewById(R.id.download_page);
        Button buttonPower = findViewById(R.id.power_check);
        buttonNotify.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NotifyActivity.class);
            startActivity(intent);
        });

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertChose();
            }
        });
        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PowerCheckActivity.class);
                startActivity(intent);
            }
        });
    }




    public void alertChose(){
        View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view,null);
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

}