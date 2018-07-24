package com.example.working;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gowsik.R;

public class WorkingList extends Activity {
	ListView list;
	String items[] = { "Toast_Notification","RattingBars","TimeAndDate","TextWatchers","Camera","MusicPlayer","Bluetooth","Wifi","WiFiDemo" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.working_list);
		list = (ListView) findViewById(R.id.work_list);
		ArrayAdapter<String> array = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		list.setAdapter(array);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				try {
					Class name = Class.forName("com.example.working."
							+ list.getItemAtPosition(arg2).toString());
					Intent intent = new Intent(WorkingList.this, name);
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(),
							"Selecting class failed..", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});

	}

}
