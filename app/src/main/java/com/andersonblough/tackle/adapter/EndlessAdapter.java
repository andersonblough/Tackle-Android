package com.andersonblough.tackle.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Bill Anderson-Blough (bill.andersonblough@gmail.com)
 */
public abstract class EndlessAdapter<T extends View> extends PagerAdapter {

    public static final int STARTING_POSITION = 15500;

    SparseArray<T> items;


    public EndlessAdapter() {
        super();
        items = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return 31000;
    }

    public int getRealCount() {
        return 7;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public abstract T createView(Context context, int position);

    public T getItem(int position) {
        return items.get(position % getRealCount());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int adjustedPosition = position % getRealCount();

        T item = createView(container.getContext(), position);
        container.addView(item);
        items.put(adjustedPosition, item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public int getStartingPosition() {
        return STARTING_POSITION - (STARTING_POSITION % 7);
    }
}
