package com.example.nmq687.apptab;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    ClockTab mClockTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("Clock").setIndicator("Clock"), ClockTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Button").setIndicator("Button"), ButtonTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Switch").setIndicator("Switch"), SwitchTab.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Spinner").setIndicator(setCustomIndicator("Spinner1", R.drawable.ic_launcher)), SpinnerTab.class, null);

        mTabHost.setCurrentTab(1);
        mTabHost.getTabWidget().setStripEnabled(true);
    }

    public View setCustomIndicator(String inText, int resId) {
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null, false);
        ((TextView)tabIndicator.findViewById(R.id.title)).setText(inText);
        ((ImageView)tabIndicator.findViewById(R.id.icon)).setImageResource(resId);
        return tabIndicator;
    }

    public void cbPressed(View view) {
        mClockTabFragment.clockLabel.setText("Pressed");
    }
}
