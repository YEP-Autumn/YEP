package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapace.R;

import java.util.ArrayList;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgAdapterHandler> {

    List<String> msg;
    Context context;

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }

    public MsgAdapter(List<String> msg, Context context) {
        this.msg = msg;
        this.context = context;
    }

    @NonNull
    @Override
    public MsgAdapterHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.msg_view, null);
        return new MsgAdapterHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapterHandler holder, int position) {
        holder.textView.setText(msg.get(position));
    }

    @Override
    public int getItemCount() {
        return msg == null ? 0 : msg.size();
    }

    class MsgAdapterHandler extends RecyclerView.ViewHolder {
        TextView textView;

        public MsgAdapterHandler(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.msg_item);
        }
    }
}
