package com.example.colocatoronboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager sliveViewPager;
    private LinearLayout dotLayout;
    private Button skipButton;

    private SliderAdapter slideAdapter;
    private TextView[] dots;

    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        sliveViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        skipButton = (Button) findViewById(R.id.skip);

        slideAdapter = new SliderAdapter(this);

        sliveViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        sliveViewPager.addOnPageChangeListener(viewListener);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage < 3) {
                    sliveViewPager.setCurrentItem(currentPage + 1);
                } else {
                    //TODO Switch to InAppActivity
                }
            }
        });
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[3];
        dotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorGrey));

            dotLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            for (int i = 0; i <= position; i++) {
                dots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;

            if (position == 0) {
                skipButton.setEnabled(false);
                skipButton.setVisibility(View.INVISIBLE);
                skipButton.setText("");
            } else {
                skipButton.setEnabled(true);
                skipButton.setVisibility(View.VISIBLE);
                skipButton.setText("Skip");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
