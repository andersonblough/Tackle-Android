package com.andersonblough.tackle.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andersonblough.tackle.R;
import com.andersonblough.tackle.util.BADateUtil;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Bill Anderson-Blough (bill.andersonblough@gmail.com)
 */
public class DayTab extends LinearLayout {

    @Bind(R.id.day_of_week)
    TextView dayOfWeek;

    @Bind(R.id.day_of_month)
    TextView dayOfMonth;

    public DayTab(Context context) {
        this(context, null);
    }

    public DayTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.day_tab, this);
        ButterKnife.bind(this);
    }

    public void setDate(Date date) {
        dayOfMonth.setText(BADateUtil.dayOfMonth(date).toLowerCase());
        dayOfWeek.setText(BADateUtil.dayOfWeek(date).toLowerCase());
    }

    public void setSelected(boolean selected) {
        transform(selected ? 1f : 0f);
    }

    public void transform(float ratio) {
        float textSizeLarge = getContext().getResources().getDimension(R.dimen.text_size_x_large);
        float textSizeMedium = getContext().getResources().getDimension(R.dimen.text_size_medium);
        float textSizeSmall = getContext().getResources().getDimension(R.dimen.text_size_small);

        float dayRatio = 1f - ratio;

        final float dayTextSize = textSizeSmall * dayRatio;
        final float dateTextSize = Math.max(textSizeMedium, textSizeLarge * ratio);

        post(new Runnable() {
            @Override
            public void run() {
                dayOfMonth.setTextSize(TypedValue.COMPLEX_UNIT_PX, dateTextSize);
                dayOfWeek.setTextSize(TypedValue.COMPLEX_UNIT_PX, dayTextSize);
            }
        });
    }
}
