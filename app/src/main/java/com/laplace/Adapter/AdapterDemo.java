package com.laplace.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapace.R;

import java.util.List;

public final class AdapterDemo extends BaseAdapter {

    private Context context;
    private List<String> list;
    private List<Integer> listImg;

    public AdapterDemo(Context context, List<String> list,List<Integer> listImg) {
        this.context = context;
        this.list = list;
        this.listImg = listImg;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list == null ? null : list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_view_item,viewGroup,false);
            viewHolder.textView = view.findViewById(R.id.item_text);
            viewHolder.imageView = view.findViewById(R.id.item_img);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.textView.setText(list.get(i));
        if(listImg != null) viewHolder.imageView.setImageResource(listImg.size() > i ? listImg.get(i) : listImg.get(0));
        return view;

    }

    private final class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }
}
