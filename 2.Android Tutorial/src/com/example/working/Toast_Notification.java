package com.example.working;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gowsik.R;

public class Toast_Notification extends Activity {
	RadioButton sample, custome;
	Button top, bottom, left, right, center;
	View view;
	TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toast_notification);
		sample = (RadioButton) findViewById(R.id.radio0);
		custome = (RadioButton) findViewById(R.id.radio1);
		top = (Button) findViewById(R.id.top_toast);
		bottom = (Button) findViewById(R.id.bottom_toast);
		right = (Button) findViewById(R.id.right_toast);
		left = (Button) findViewById(R.id.left_toast);
		center = (Button) findViewById(R.id.center_toast);

		view = getLayoutInflater().inflate(R.layout.toast_layout,
				(ViewGroup) findViewById(R.id.custom_toast));
		text = (TextView) view.findViewById(R.id.text_toast);

		top.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(), "",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 0, 0);

				if (custome.isChecked()) {
					text.setText("This is Custom Toast");
					toast.setView(view);
					toast.show();

				} else {
					toast.setText("This is Simple Toast");
					toast.show();

				}

			}
		});
		bottom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(), "",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.BOTTOM, 0, 0);

				if (custome.isChecked()) {
					text.setText("This is Custom Toast");
					toast.setView(view);
					toast.show();

				} else {
					toast.setText("This is Simple Toast");
					toast.show();

				}

			}
		});
		left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(), "",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.LEFT, 0, 0);

				if (custome.isChecked()) {
					text.setText("This is Custom Toast");
					toast.setView(view);
					toast.show();

				} else {
					toast.setText("This is Simple Toast");
					toast.show();

				}

			}
		});

		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(), "",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.RIGHT, 0, 0);

				if (custome.isChecked()) {
					text.setText("This is Custom Toast");
					toast.setView(view);
					toast.show();

				} else {
					toast.setText("This is Simple Toast");
					toast.show();

				}

			}

		});
		center.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(), "",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);

				if (custome.isChecked()) {
					text.setText("This is Custom Toast");
					toast.setView(view);
					toast.show();

				} else {
					toast.setText("This is Simple Toast");
					toast.show();

				}

			}
		});

	}

}
