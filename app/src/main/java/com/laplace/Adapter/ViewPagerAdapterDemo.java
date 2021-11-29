package com.laplace.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ViewPagerAdapterDemo extends PagerAdapter {
    public ViewPagerAdapterDemo(List<View> viewList) {
        this.viewList = viewList;
    }

    private List<View> viewList;

    @Override
    public int getCount() {
        return viewList == null ? 0 : viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position),0);
        return viewList.get(position);
    }
}
