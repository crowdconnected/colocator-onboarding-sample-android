package com.example.colocatoronboarding;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import static android.location.LocationManager.GPS_PROVIDER;

public class SliderAdapter extends PagerAdapter {

    Activity activity;
    Context context;
    LayoutInflater layoutInflater;

    public int[] icons = {
            R.drawable.colo,
            R.drawable.privacy,
            R.drawable.marker
    };
    public String[] titles = {
            "Welcome",
            "Privacy",
            "Location"
    };
    public String[] descriptions = {
            "Welcome to Colocator Onboarding experience",
            "Lorem ipsum dolor sit amet, per ad commodo scaevola explicari, vel ex quis assum dolorem. Unum ullum maiestatis nam at. Tation repudiandae usu ut.",
            "Lorem ipsum dolor sit amet, per ad commodo scaevola explicari, vel ex quis assum dolorem. Unum ullum maiestatis nam at. Tation repudiandae usu ut."
    };
    public String[] actions = {
            "Start",
            "Accept",
            "Allow Location"
    };

    public SliderAdapter(Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = (View) layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView description = (TextView) view.findViewById(R.id.description);
        Button action = (Button) view.findViewById(R.id.action);

        image.setImageResource(icons[position]);
        title.setText(titles[position]);
        description.setText(descriptions[position]);
        action.setText(actions[position]);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titles[position] == "Welcome") {
                    if (activity instanceof OnboardingActivity) {
                        ((OnboardingActivity) activity).goToNextPage();
                    }
                    return;
                }

                if (titles[position] == "Privacy") {
                    if (activity instanceof OnboardingActivity) {
                        ((OnboardingActivity) activity).goToNextPage();
                    }
                    return;
                }

                if (titles[position] == "Location") {
                    requestLocationPermission();
                    return;
                }
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }

    public void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        boolean statusOfGPS = locationManager.isProviderEnabled(GPS_PROVIDER);

        if (!statusOfGPS) {
            requestGps();
        }
    }

    public void requestGps() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("GPS settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}
