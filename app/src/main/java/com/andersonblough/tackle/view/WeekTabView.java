package com.andersonblough.tackle.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.andersonblough.tackle.R;
import com.andersonblough.tackle.util.BADateUtil;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Bill Anderson-Blough (bill.andersonblough@gmail.com)
 */
public class WeekTabView extends LinearLayout {

    @Bind({R.id.day1, R.id.day2, R.id.day3, R.id.day4, R.id.day5, R.id.day6, R.id.day7})
    List<DayTab> dayTabs;

    private int selectedPos;
    private WeekListener listener;
    private Date date;

    public interface WeekListener {
        void onDaySelected(int position);
    }

    public WeekTabView(Context context) {
        this(context, null);
    }

    public WeekTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.week_tab_view, this);
        ButterKnife.bind(this);
    }

    public void setListener(WeekListener listener) {
        this.listener = listener;
    }

    public void setDate(Date date) {
        this.date = BADateUtil.firstDayOfWeek(date);
        ButterKnife.apply(dayTabs, new ButterKnife.Action<DayTab>() {
            @Override
            public void apply(DayTab view, int index) {
                view.setDate(BADateUtil.addDays(WeekTabView.this.date, index));
            }
        });
    }

    public void setSelected(final int position) {
        selectedPos = position;
        ButterKnife.apply(dayTabs, new ButterKnife.Action<DayTab>() {
            @Override
            public void apply(DayTab view, final int index) {
                view.setSelected(index == position);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onDaySelected(index);
                    }
                });
            }
        });
    }

    public void transform(int currentPos, int nextPos, float offset) {
        dayTabs.get(currentPos).transform(offset);
        dayTabs.get(nextPos).transform(1f - offset);
    }
}
