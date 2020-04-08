package com.example.colocatoronboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InAppActivity extends AppCompatActivity {

    InAppFragment inAppFragment;
    ManualLocationPermissionFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app);

        inAppFragment = new InAppFragment();
        settingsFragment = new ManualLocationPermissionFragment(InAppActivity.this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, inAppFragment);
        fragmentTransaction.commit();
    }

    public void goToLocationScreen(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, settingsFragment);
        fragmentTransaction.commit();
    }

    public void goToMainScreen(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, inAppFragment);
        fragmentTransaction.commit();
    }

    public void openSettings(View view) {
        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
    }
}
