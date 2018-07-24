package com.example.gowsik;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UsersDetails extends Activity {
	private ListView list;
	private SQLiteDatabase sql;
	private static ArrayList<String> name;
	private int count=0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users_details);
		Initials();
		
		Cursor cr=sql.rawQuery("select name from userdetail", null);
		
		if((cr!=null)&(cr.moveToFirst())){
			
			do{
				name.add(cr.getString(0));
				count++;
			}while(cr.moveToNext());
			
		}else {
			name.add("No Records Available");
		}
		
		ArrayAdapter<String> array=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name);
		list.setAdapter(array);
		Toast.makeText(getApplicationContext(), name.get(0), Toast.LENGTH_SHORT).show();
		
		
	}


	private void Initials() {
		// TODO Auto-generated method stub
		list=(ListView)findViewById(R.id.list);
		sql = openOrCreateDatabase("user", MODE_PRIVATE, null);
		name=new ArrayList<String>();
		sql.execSQL("create table if not exists userdetail(name text,email text,phone text,username text,password text);");
		
	}
	
	

	

}
