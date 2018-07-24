package com.example.gowsik;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminPage extends Activity {

	Button a_login;
	EditText a_user, a_pass;
	TextView a_clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_page);
		initialise();
		
		a_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String a = a_user.getText().toString();
				String b = a_pass.getText().toString();
				if ((a.equals("admin")) && (b.equals("admin"))) {
					Intent next=new Intent(AdminPage.this,UsersDetails.class);
					startActivity(next);

				} else {
					Toast.makeText(getApplicationContext(),
							"You are not admin", Toast.LENGTH_SHORT).show();
				}

			}
		});
		a_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				a_user.setText("");
				a_pass.setText("");		
							
			}
		});

	}

	private void initialise() {
		// TODO Auto-generated method stub
		a_login = (Button) findViewById(R.id.a_login);
		a_user = (EditText) findViewById(R.id.a_username);
		a_pass = (EditText) findViewById(R.id.a_password);
		a_clear = (TextView) findViewById(R.id.a_clear);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	
}
