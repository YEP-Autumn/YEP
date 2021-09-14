package com.example.chat;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChatHandler extends Handler {
    public ChatHandler(@NonNull Looper looper) {
        super(looper);
    }

    public ChatHandler(@NonNull Looper looper, @Nullable Callback callback) {
        super(looper, callback);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
    }

    @Override
    public void dispatchMessage(@NonNull Message msg) {
        super.dispatchMessage(msg);
    }

    @NonNull
    @Override
    public String getMessageName(@NonNull Message message) {
        return super.getMessageName(message);
    }

    @Override
    public boolean sendMessageAtTime(@NonNull Message msg, long uptimeMillis) {
        return super.sendMessageAtTime(msg, uptimeMillis);
    }

}
