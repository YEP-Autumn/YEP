package com.laplace.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapace.R;

import java.util.List;

public class ViewPager2AdapterDemo extends RecyclerView.Adapter<ViewPager2AdapterDemo.ViewHolder> {
    private Context context;
    private List<Drawable> imgList;

    public ViewPager2AdapterDemo(Context context, List<Drawable> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.image_view, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (imgList == null) return;
        holder.imageView.setImageDrawable(imgList.get(position));
    }

    @Override
    public int getItemCount() {
        return imgList == null ? 0 : imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

}
