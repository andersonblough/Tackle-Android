package com.andersonblough.tackle.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.andersonblough.tackle.adapter.EndlessAdapter;
import com.andersonblough.tackle.util.BADateUtil;

import java.util.Date;

/**
 * @author Bill Anderson-Blough (bill.andersonblough@gmail.com)
 */
public class WeekPager extends ViewPager {

    private WeekAdapter weekAdapter;
    private Date date;
    private int startingPos;

    public WeekPager(Context context) {
        this(context, null);
    }

    public WeekPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        weekAdapter = new WeekAdapter();
        date = new Date();
        setOffscreenPageLimit(3);
        setAdapter(weekAdapter);
        startingPos = weekAdapter.getStartingPosition() + BADateUtil.weekday(date);
        setCurrentItem(startingPos);
    }

    // set current position
    public void setCurrentPosition(int position) {
        int offset = position - (getCurrentItem() % weekAdapter.getRealCount());
        setCurrentItem(getCurrentItem() + offset, true);
    }

    public Page getPage(int position) {
        return weekAdapter.getItem(position);
    }

    public int getCurrentPosition() {
        return getCurrentItem() % weekAdapter.getRealCount();
    }

    public class WeekAdapter extends EndlessAdapter<Page> {

        @Override
        public Page createView(Context context, int position) {
            int dayAdjustment = position - startingPos;
            Page page = new Page(context);
            page.setDate(BADateUtil.addDays(date, dayAdjustment));
            return page;
        }
    }
}
