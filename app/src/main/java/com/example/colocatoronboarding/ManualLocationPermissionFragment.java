package com.example.colocatoronboarding;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManualLocationPermissionFragment extends Fragment {

    private Activity activity;
    private int resumedTimes = 0;
    private boolean goToSettingsActive = false;

    public ManualLocationPermissionFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manual_location_permission, container, false);
    }

    public void didTapGoToSettings() {
        goToSettingsActive = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Flow", "On resume ");
        resumedTimes += 1;

        if (resumedTimes %2 == 0 && goToSettingsActive) {
            ((InAppActivity) activity).goToMainScreen(null);
            goToSettingsActive = false;
        }
    }
}
