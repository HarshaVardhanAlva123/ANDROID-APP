 package com.example.gowsik;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.working.WorkingList;

public class MainActivity extends Activity {
	private Button login;
	private EditText username, password;
	private TextView signup, clear;
	private CheckBox showpass;
	private String get_username, get_passwor;
	private SQLiteDatabase sq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		addressing();

		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent newxt = new Intent(MainActivity.this, Signup.class);
				startActivity(newxt);
			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				get_username = username.getText().toString();
				get_passwor = password.getText().toString();
				if ((get_username.length() > 0) & (password.length() > 0)) {
					if (veryfying()) {
						Intent next = new Intent(MainActivity.this,
								WorkingList.class);
						startActivity(next);

					} else {
						Toast.makeText(getApplicationContext(),
								"Username or password is not valid",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Enter username and password", Toast.LENGTH_SHORT)
							.show();
				}

			}

		});
		showpass.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// checkbox status is changed from uncheck to checked.
				if (!isChecked) {
					// show password
					password.setTransformationMethod(PasswordTransformationMethod
							.getInstance());
				} else {
					// hide password
					password.setTransformationMethod(HideReturnsTransformationMethod
							.getInstance());
				}
			}
		});
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				username.setText("");
				password.setText("");

			}
		});

	}

	protected void addressing() {
		// TODO Auto-generated method stub
		login = (Button) findViewById(R.id.login);
		username = (EditText) findViewById(R.id.l_username);
		password = (EditText) findViewById(R.id.l_password);
		clear = (TextView) findViewById(R.id.clear);
		signup = (TextView) findViewById(R.id.signup);
		showpass = (CheckBox) findViewById(R.id.show_pass);
		sq = openOrCreateDatabase("user", MODE_PRIVATE, null);
	}

	protected boolean veryfying() {
		// TODO Auto-generated method stub
		Cursor cr = sq.rawQuery(
				"select password from userdetail where username='"
						+ get_username + "';", null);
		String checking_pass = null;
		if ((cr != null) & (cr.moveToFirst())) {
			checking_pass = cr.getString(0);
			return (checking_pass.equals(get_passwor));
		}      
		return false;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true; 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.admin:
			Intent log = new Intent(MainActivity.this, AdminPage.class);
			startActivity(log);
			break;  
		case R.id.action_settings:
			Intent log1 = new Intent(MainActivity.this, WorkingList.class);
			startActivity(log1);
			break;

		default: 
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		username.setText("");
		password.setText("");
	}
}
