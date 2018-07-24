package com.example.gowsik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends Activity {
	private EditText name, email, phone, user, pass, c_pass;
	private Button signup;
	private TextView clear;
	private SQLiteDatabase sq;
	private String g_user, g_pass, g_cpass, g_email, g_name, g_phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_page);
		Initial();
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				name.setText("");
				email.setText("");
				phone.setText("");
				user.setText("");
				pass.setText("");
				c_pass.setText("");

			}
		});
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getValues();
				validate();
			}
		});

	}

	protected void validate() {
		if ((g_name.length() > 0) & (g_email.length() > 0)
				& (g_phone.length() > 0) & (g_user.length() > 0)
				& (g_cpass.length() > 0) & (g_pass.length() > 0)) {
			// TODO Auto-generated method stub

			if (!isValidName(g_name)) {
				name.setError("Please enter full name");
			} else if (!isValidEmail(g_email)) {
				email.setError("Invalid Email");
			} else if (!isValidPhone(g_phone)) {
				phone.setError("This not valid Phone number");
			} else if (!(g_user.length() > 5)) {
				user.setError("Username must be more then 5 letters");
			} else if (!isValidPassword(g_pass)) {
				pass.setError("Password minimum length 6 digit");
			} else if (!g_pass.equals(g_cpass)) {
				c_pass.setError("Confirmation password is not match");
			}else {
				addDetails();
				Toast.makeText(getApplicationContext(),
						"Sucess", Toast.LENGTH_SHORT).show();
				finish();
			}

		} else {
			Toast.makeText(getApplicationContext(),
					"You can't leave fields empty.", Toast.LENGTH_SHORT).show();
		}

	}

	private void addDetails() {
		// TODO Auto-generated method stub
		sq.execSQL("insert into userdetail values('"+g_name+"','"+g_email+"','"+g_phone+"','"+g_user+"','"+g_pass+"');");
			}

	protected void getValues() {
		// TODO Auto-generated method stub
		g_name = name.getText().toString();
		g_email = email.getText().toString();
		g_phone = phone.getText().toString();
		g_user = user.getText().toString();
		g_pass = pass.getText().toString();
		g_cpass = c_pass.getText().toString();
	}

	private void Initial() {
		// TODO Auto-generated method stub
		name = (EditText) findViewById(R.id.name);
		email = (EditText) findViewById(R.id.email);
		phone = (EditText) findViewById(R.id.phone);
		signup = (Button) findViewById(R.id.signup_b);
		user = (EditText) findViewById(R.id.s_username);
		pass = (EditText) findViewById(R.id.s_password);
		c_pass = (EditText) findViewById(R.id.r_password);
		clear = (TextView) findViewById(R.id.clear_all);
		sq = openOrCreateDatabase("user", MODE_PRIVATE, null);
		sq.execSQL("create table if not exists userdetail(name text,email text,phone text,username text,password text);");

	}

	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	// validating password with retype password
	private boolean isValidPassword(String pass) {
		if (pass != null && pass.length() >= 6) {
			return true;
		}
		return false;
	}

	private boolean isValidName(String name) {
		if (name != null && name.length() >= 6) {
			return true;
		}
		return false;
	}

	private boolean isValidPhone(String phone) {
		if (phone != null && phone.length() >= 10) {
			return true;
		}
		return false;
	}
	


}
