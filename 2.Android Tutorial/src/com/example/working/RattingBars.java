package com.example.working;

import com.example.gowsik.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class RattingBars extends Activity {

	RatingBar ratting;
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratting_bars);
        text = (TextView) findViewById(R.id.text);
        ratting = (RatingBar) findViewById(R.id.ratingBar);
        ratting.setStepSize(5);
        text.setText("Your rating is " + ratting.getRating());
        ratting.setStepSize((float) 0.5);
        ratting.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                text.setText("Your rating is " + v);

            }
        });
    }
}
