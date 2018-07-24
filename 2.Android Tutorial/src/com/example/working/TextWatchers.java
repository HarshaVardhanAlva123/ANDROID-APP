package com.example.working;

import com.example.gowsik.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class TextWatchers extends Activity {

	EditText after_change, before_change, on_change;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_watcher);
		after_change = (EditText) findViewById(R.id.after_change);
		before_change = (EditText) findViewById(R.id.before_change);
		on_change = (EditText) findViewById(R.id.on_change);
		display = (TextView) findViewById(R.id.text_change);
		before_change.addTextChangedListener(beforeWatcher);
		after_change.addTextChangedListener(afterWatcher);
		on_change.addTextChangedListener(onWatcher);
	}

	private TextWatcher beforeWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			display.setText(arg0);

		}

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub

		}
	};
	private TextWatcher afterWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if (s.length() < 6) {
				
				display.setTextColor(Color.RED);
				display.setText("EASY");
			} else if (s.length() < 10) {
				display.setTextColor(Color.YELLOW);
				
				display.setText("MEDIUM");
			} else if (s.length() < 15) {
				display.setTextColor(Color.MAGENTA);
				
				display.setText("STRONG");
			} else {
				//display.setTextColor(Color.BLUE);
				display.setTextColor(Color.GREEN);
				display.setText("STRONGEST");
			}

			if (s.length() >= 20) {
				display.setTextColor(Color.LTGRAY);
				display.setText("Password Max Length Reached");
			}
		}
	};
	private TextWatcher onWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			display.setText(arg0);

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub

		}
	};

}
