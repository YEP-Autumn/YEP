package com.example.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapace.R;

import java.util.List;

public class RecyclerAdapterDemo extends RecyclerView.Adapter<RecyclerAdapterDemo.ViewHolder> {

    private Context context;
    private List<String> data;
    private List<Integer> imgList;

    public RecyclerAdapterDemo(Context context,List<String> data, List<Integer> imgList) {
        this.context = context;
        this.data = data;
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.recycler_view_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
        holder.imageView.setImageResource(imgList.size()-1 < position?imgList.get(imgList.size()-1):imgList.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_text_item);
            imageView = itemView.findViewById(R.id.recycler_img_item);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onRecyclerItemClickListener!=null){
                        onRecyclerItemClickListener.onRecyclerItemClick((TextView)view,getBindingAdapterPosition());
                    }
                }
            });
        }
    }




    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener){
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }
    public interface OnRecyclerItemClickListener{
        void onRecyclerItemClick(TextView textView,int i);

    }




}






