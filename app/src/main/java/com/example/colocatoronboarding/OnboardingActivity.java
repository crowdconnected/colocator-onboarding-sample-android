package com.example.colocatoronboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotLayout;
    private Button skipButton;

    private SliderAdapter slideAdapter;
    private TextView[] dots;

    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        skipButton = (Button) findViewById(R.id.skip);

        slideAdapter = new SliderAdapter(this);

        slideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);

        slideViewPager.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage == dots.length - 1) {
                    endOnboarding();
                } else {
                    goToNextPage();
                }
            }
        });
    }

    public void goToNextPage() {
        slideViewPager.setCurrentItem(currentPage + 1);
    }

    private void endOnboarding() {
        Log.e("Flow","End Onboarding called");
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPref.edit().putBoolean("is_user_onboarded", true).commit();

        Intent activityIntent = new Intent(OnboardingActivity.this, InAppActivity.class);

        startActivity(activityIntent);
        finish();
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[3];
        dotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            dots[i].setTextColor(getResources().getColor(R.color.colorGrey));

            dotLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            for (int i = 0; i < position; i++) {
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

            if (position != dots.length - 1) {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.i("Permission", "Permission Result - Code " + requestCode);
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { } else { }
                endOnboarding();
            }
        }
    }
}
