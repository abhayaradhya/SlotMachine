package com.example.abhayaradhya.slotmachine2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

public class RulesActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView pageInd1;
    private ImageView pageInd2;
    private ImageView pageInd3;
    private ImageView pageInd4;
    private ImageView pageInd5;
    private TextView pagerText;
    private TextView scoreDisplay;

    private String[] rulesDisplay = {"To start playing, press \"START\". All three spinner will begin to spin.", "To stop each spinner, press \"STOP\". Each spinner must be stopped individually. They will stop in left-to-right order.", "Try to aim for a high score by matching the three colors! For each match, you get 500 points.", "The difficulty can be adjusted using the difficulty bar. There are three difficulties. Higher difficulties will give you more points.", "Press the back arrow to return to the game."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("score");
        System.out.println(score);

        scoreDisplay = findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(score+"");

        pageInd1 = findViewById(R.id.pageInd1);
        pageInd2 = findViewById(R.id.pageInd2);
        pageInd3 = findViewById(R.id.pageInd3);
        pageInd4 = findViewById(R.id.pageInd4);
        pageInd5 = findViewById(R.id.pageInd5);

        final ArrayList<ImageView> pageIndicators = new ArrayList<ImageView>();
        pageIndicators.add(pageInd1);
        pageInd1.setBackgroundColor(0xFFCDCDE8);
        pageIndicators.add(pageInd2);
        pageInd2.setBackgroundColor(0xFF393A54);
        pageIndicators.add(pageInd3);
        pageInd3.setBackgroundColor(0xFF393A54);
        pageIndicators.add(pageInd4);
        pageInd4.setBackgroundColor(0xFF393A54);
        pageIndicators.add(pageInd5);
        pageInd5.setBackgroundColor(0xFF393A54);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0; i<pageIndicators.size(); i++){
                    pageIndicators.get(i).setBackgroundColor(0xFF393A54);
                }
                pageIndicators.get(position).setBackgroundColor(0xFFCDCDE8);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), rulesDisplay);
        mPager.setAdapter(mPagerAdapter);

        //mPagerAdapter.
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        String[] displayText;
        ScreenSlidePageFragment fragment;

        public ScreenSlidePagerAdapter(FragmentManager fm,String[] displayText) {
            super(fm);
            this.displayText=displayText;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            //String temp = displayText[position];
            bundle.putString("displayText", displayText[position]);
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        /*public void setDisplayText(int position){
            fragment = new ScreenSlidePageFragment();
            fragment.setText(position);
        }*/
    }
}
