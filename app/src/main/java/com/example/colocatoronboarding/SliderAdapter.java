package com.example.colocatoronboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public int[] icons = {
            R.drawable.privacy,
            R.drawable.marker
    };
    public String[] titles = {
            "Privacy",
            "Location"
    };
    public String[] descriptions = {
            "Lorem ipsum dolor sit amet, per ad commodo scaevola explicari, vel ex quis assum dolorem. Unum ullum maiestatis nam at. Tation repudiandae usu ut.",
            "Lorem ipsum dolor sit amet, per ad commodo scaevola explicari, vel ex quis assum dolorem. Unum ullum maiestatis nam at. Tation repudiandae usu ut."
    };
    public String[] actions = {
            "Allow",
            "Allow Location"
    };

    public SliderAdapter(Context context) {
        this.context = context;
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
                if (titles[position] == "Allow") {
                    //TODO add popup
                    //TODO move to the next screen
                    return;
                }

                if (titles[position] == "Allow Location") {
                    //TODO add popup
                    //TODO move to the next screen
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
}
