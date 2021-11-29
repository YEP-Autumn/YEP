package com.laplace.mqttclient;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laplace.bean.TopicStr;
import com.example.lapace.R;

import java.util.List;
import java.util.function.Consumer;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private Context context;
    public List<TopicStr> topics;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            topic = itemView.findViewById(R.id.topic_name);
            qoS = itemView.findViewById(R.id.qoS);
            message = itemView.findViewById(R.id.new_message);
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (topicHandler != null) {
                        topicHandler.setTopicOnclickListener(name.getText().toString());
                    }
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
                topics.set(i, tS);
                break;
            }
        }

    }
}
