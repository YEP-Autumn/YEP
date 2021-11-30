package com.laplace.mqttclient;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laplace.bean.TopicStr;
import com.example.lapace.R;

import java.sql.Timestamp;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private Context context;
    private List<TopicStr> topics;

    public TopicAdapter(Context context, List<TopicStr> topics) {
        this.context = context;
        this.topics = topics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.topic_view, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.ViewHolder holder, int position) {
        TopicStr topicStr = topics.get(position);
        holder.name.setText(topicStr.getName());
        holder.topic.setText(topicStr.getTopic());
        holder.qoS.setText(String.valueOf(topicStr.getQoS()));
        holder.message.setText(topicStr.getMessage());
    }


    @Override
    public int getItemCount() {
        return topics == null ? 0 : topics.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView topic;
        TextView qoS;
        TextView message;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            topic = itemView.findViewById(R.id.topic_name);
            qoS = itemView.findViewById(R.id.qoS);
            message = itemView.findViewById(R.id.new_message);
            button = itemView.findViewById(R.id.un_subscribe);
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (topicHandler != null) {
//                        topicHandler.setMessageOnclickListener(name.getText().toString());
                        topicHandler.setMessageOnclickListener(topics.get(getBindingAdapterPosition()).getTopic(), topics.get(getBindingAdapterPosition()).getMessagesList());
                    }
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (topicHandler != null) {
                        topicHandler.setUnSubscribe(topics.get(getBindingAdapterPosition()).getTopic(), getBindingAdapterPosition());
                    }
                    topics.remove(getBindingAdapterPosition());
                    notifyItemRemoved(getBindingAdapterPosition());
                }
            });
        }
    }

    private TopicOnClickListener topicHandler;

    public void setOnClickListener(TopicOnClickListener topicHandler) {
        this.topicHandler = topicHandler;
    }

    public void updateTopic(TopicStr topicStr) {
        for (int i = 0; i < topics.size(); i++) {
            TopicStr tS = topics.get(i);
            if (tS.getTopic().equals(topicStr.getTopic())) {
                tS.setMessage(topicStr.getMessage());
                tS.addMessage(new Timestamp(System.currentTimeMillis()), topicStr.getMessage());
                topics.set(i, tS);
                break;
            }
        }
    }

    public boolean add(TopicStr str) {
        if (topics.contains(str)) return false;
        topics.add(str);
        return true;
    }
}
