package com.andersonblough.tackle.view;

import android.animation.Animator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.andersonblough.tackle.R;
import com.andersonblough.tackle.util.BADateUtil;
import com.andersonblough.tackle.util.SimpleAnimatorListener;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author Bill Anderson-Blough (bill.andersonblough@gmail.com)
 */
public class WeekTab extends FrameLayout implements WeekTabView.WeekListener {

    @Bind(R.id.week_view)
    WeekTabView weekTabView;

    private Date date;
    private WeekPager weekPager;

    public WeekTab(Context context) {
        this(context, null);
    }

    public WeekTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.week_view, this);
        ButterKnife.bind(this);
    }

    public void init(Date date, WeekPager weekPager) {
        setWeekPager(weekPager);
        setDate(date);
    }

    public void setDate(Date date) {
        this.date = date;
        weekTabView.setDate(date);
        weekTabView.setSelected(weekPager.getCurrentPosition());
        weekTabView.setListener(this);
    }

    public void setWeekPager(final WeekPager weekPager) {
        this.weekPager = weekPager;
        weekPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int pos = (position + 1) % 7;
                int nextPos = pos == 0 ? 6 : pos - 1;

                if ((pos != 0) && (pos != 6 || nextPos != 0)) {
                    weekTabView.transform(pos, nextPos, positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                Page page = weekPager.getPage(position);
                if (BADateUtil.isNextWeek(date, page.getDate())) {
                    nextWeek();
                } else if (BADateUtil.isLastWeek(date, page.getDate())) {
                    previousWeek();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void nextWeek() {
        Date sunday = BADateUtil.nextWeek(date);
        sunday = BADateUtil.firstDayOfWeek(sunday);
        this.date = sunday;

        final WeekTabView newTabView = new WeekTabView(getContext());
        newTabView.setLayoutParams(weekTabView.getLayoutParams());
        addView(newTabView);
        newTabView.setDate(sunday);
        newTabView.setListener(this);
        newTabView.setSelected(weekPager.getCurrentPosition());
        newTabView.setTranslationX(weekTabView.getWidth());

        weekTabView.animate()
                .translationX(-weekTabView.getWidth())
                .setListener(null)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
        newTabView.animate()
                .translationX(0)
                .setListener(new SimpleAnimatorListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        removeView(weekTabView);
                        weekTabView = newTabView;
                    }
                })
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    private void previousWeek() {
        Date saturday = BADateUtil.previousWeek(date);
        saturday = BADateUtil.lastDayOfWeek(saturday);
        this.date = saturday;

        final WeekTabView newTabView = new WeekTabView(getContext());
        newTabView.setLayoutParams(weekTabView.getLayoutParams());
        addView(newTabView);
        newTabView.setDate(saturday);
        newTabView.setListener(this);
        newTabView.setSelected(weekPager.getCurrentPosition());
        newTabView.setTranslationX(-weekTabView.getWidth());

        weekTabView.animate()
                .translationX(weekTabView.getWidth())
                .setListener(null)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
        newTabView.animate()
                .translationX(0)
                .setListener(new SimpleAnimatorListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        removeView(weekTabView);
                        weekTabView = newTabView;
                        Log.d("", "");
                    }
                })
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    @Override
    public void onDaySelected(int position) {
        weekPager.setCurrentPosition(position);
    }
}
