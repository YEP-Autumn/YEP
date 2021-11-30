package com.laplace.mqttclient;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapace.R;
import com.laplace.bean.Message;

import java.util.List;


public class MessageDetailAdapter extends RecyclerView.Adapter<MessageDetailAdapter.ViewHolder> {


    Context context;

    List<Message> messageList;

    public MessageDetailAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.message_detail, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.time.setText(message.getTimestamp().toString());
        holder.message.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time_stamp);
            message = itemView.findViewById(R.id.message_detail);
        }
    }
}
