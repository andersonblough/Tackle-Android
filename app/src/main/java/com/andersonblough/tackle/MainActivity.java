package com.andersonblough.tackle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.andersonblough.tackle.view.WeekPager;
import com.andersonblough.tackle.view.WeekTab;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private static final String ARGS_DATE = "date";

    @Bind(R.id.pager)
    WeekPager weekPager;

    @Bind(R.id.week_tab)
    WeekTab weekTab;

    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            date = new Date(savedInstanceState.getLong(ARGS_DATE));
        } else {
            date = new Date();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        weekTab.init(date, weekPager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.date = weekPager.getPage(weekPager.getCurrentPosition()).getDate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(ARGS_DATE, date.getTime());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
