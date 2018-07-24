package com.example.working;

import java.util.Calendar;

import com.example.gowsik.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeAndDate extends Activity {

	Button date_b, time_b;
	TextView d_view, t_view;

	int hour, min, month, date, year;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_and_date);
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		min = calendar.get(Calendar.MINUTE);
		month = calendar.get(Calendar.MONTH);
		date = calendar.get(Calendar.DAY_OF_MONTH);
		year = calendar.get(Calendar.YEAR);
		date_b = (Button) findViewById(R.id.date);
		time_b = (Button) findViewById(R.id.time);
		d_view = (TextView) findViewById(R.id.date_view);
		t_view = (TextView) findViewById(R.id.time_view);

		date_b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				DatePickerDialog datePicker = new DatePickerDialog(
						TimeAndDate.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								d_view.setText(dayOfMonth + "-"
										+ (monthOfYear + 1) + "-" + year);

							}
						}, year, month, date);
				datePicker.show();

			}
		});
		time_b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// showDialog(1);
				TimePickerDialog timePickerDialog = new TimePickerDialog(
						TimeAndDate.this,
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {

								t_view.setText(hourOfDay + ":" + minute);
							}
						}, hour, min, true);
				timePickerDialog.show();

			}
		});
	}

}
