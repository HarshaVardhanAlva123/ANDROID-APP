package com.example.working;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.gowsik.R;

public class Bluetooth extends Activity {
	ToggleButton bluetooth_On;
	ImageView bluetooth_image;
	ListView paired_device;
	Button searchDevice, list_device;
	TextView status;
	private Set<BluetoothDevice> pairedDevices;
	BluetoothAdapter bluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth);
		initial();
		bluetooth_On.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean blue) {
				// TODO Auto-generated method stub
				if ((blue) & (!bluetoothAdapter.isEnabled())) {
					status.setText("Bluetooth Turned On");
					bluetooth_image.setBackgroundResource(R.drawable.bluetooth);
					searchDevice.setVisibility(View.VISIBLE);
					searchDevice.setText("Search");
					Intent turnon = new Intent(
							BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivityForResult(turnon, 0);
				} else {
					status.setText("Bluetooth Turned Off");
					searchDevice.setVisibility(View.GONE);
					bluetooth_image.setBackgroundResource(0);
					searchDevice.setVisibility(View.GONE);
					list_device.setVisibility(View.GONE);
					paired_device.setVisibility(View.GONE);
					bluetoothAdapter.disable();

				}
			}
		});
		searchDevice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stubse
				status.setText("Searching nearby device");
				searchDevice.setText("Searching...");
				bluetooth_image
						.setBackgroundResource(R.drawable.bluetooth_searching);
				list_device.setVisibility(View.VISIBLE);
				Intent getVisible = new Intent(
						BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				startActivityForResult(getVisible, 0);

			}
		});
		list_device.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				status.setText("");
				paired_device.setVisibility(View.VISIBLE);
				bluetooth_image
						.setBackgroundResource(R.drawable.bluetooth_connected);
				pairedDevices = bluetoothAdapter.getBondedDevices();
				ArrayList<String> li = new ArrayList<String>();
				for (BluetoothDevice bt : pairedDevices)
					li.add(bt.getName());
				ArrayAdapter adapter = new ArrayAdapter(Bluetooth.this,
						android.R.layout.simple_list_item_1, li);
				paired_device.setAdapter(adapter);

			}
		});
	}

	private void initial() {
		// TODO Auto-generated method stub
		bluetooth_On = (ToggleButton) findViewById(R.id.bluetooth_on);
		bluetooth_image = (ImageView) findViewById(R.id.bluetooth_image);
		paired_device = (ListView) findViewById(R.id.paired_device);
		searchDevice = (Button) findViewById(R.id.search_device);
		list_device = (Button) findViewById(R.id.view_device);
		status = (TextView) findViewById(R.id.bluetooth_status);
		searchDevice.setVisibility(View.GONE);
		list_device.setVisibility(View.GONE);
		paired_device.setVisibility(View.GONE);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bluetoothAdapter.isEnabled())
			bluetooth_On.setChecked(true);

	}

}
