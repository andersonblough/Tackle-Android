package com.andersonblough.tackle.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.andersonblough.tackle.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Bill Anderson-Blough (bill.andersonblough@gmail.com)
 */
public class Page extends FrameLayout {

    @Bind(R.id.date_field)
    TextView dateField;

    private Date date;

    public Page(Context context) {
        this(context, null);
    }

    public Page(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Page(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.page, this);
        ButterKnife.bind(this);
    }

    public void setDate(Date date) {
        this.date = date;
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        dateField.setText(formatter.format(date));
    }

    public Date getDate() {
        return date;
    }
}
