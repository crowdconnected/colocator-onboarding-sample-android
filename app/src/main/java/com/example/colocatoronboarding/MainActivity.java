package com.example.colocatoronboarding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent activityIntent;

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isOnboarded = sharedPref.getBoolean("is_user_onboarded", false);

        if (isOnboarded) {
            activityIntent = new Intent(this, InAppActivity.class);
        } else {
            activityIntent = new Intent(this, OnboardingActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }
}
