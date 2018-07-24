package com.example.working;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.gowsik.R;

public class Wifi extends Activity {

	ToggleButton wifi_On;
	ImageView wifi_image;
	ListView wifipaired_device;
	TextView status;
	WifiManager wifiManager;
	List<ScanResult> result;
	Button refresh;
	int size = 0;
	ArrayAdapter<String> adapter;
	ArrayList<String> wifi_list=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi_layout);
		initial();
		wifi_On.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean blue) {
				// TODO Auto-generated method stub
				if ((blue) & (!wifiManager.isWifiEnabled())) {
					status.setText("Wifi Turned On");
					wifi_image.setBackgroundResource(R.drawable.wifi);
					wifiManager.setWifiEnabled(true);

				} else {
					status.setText("Wifi Turned Off");
					wifiManager.setWifiEnabled(false);

				}
			}
		});
		wifi_list.add("Searching...");
		adapter=new ArrayAdapter<String>(Wifi.this, android.R.layout.simple_list_item_1,wifi_list);
		wifipaired_device.setAdapter(adapter);

		registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				result = wifiManager.getScanResults();
				size = result.size();

			}
		}, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
					// TODO Auto-generated method stub
					wifi_list.clear();
					wifiManager.startScan();
					Toast.makeText(getApplicationContext(),
							"Scanning...." + size, Toast.LENGTH_SHORT).show();
					try {
						size = size - 1;

						while (size >= 0) {

							wifi_list.add(result.get(size).SSID + "  "
									+ result.get(size).capabilities);
							size--;
							adapter.notifyDataSetInvalidated();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "failed",
								Toast.LENGTH_SHORT).show();
					
				}

			}
		});

	}

	private void initial() {
		// TODO Auto-generated method stub
		
		wifi_On = (ToggleButton) findViewById(R.id.wifi_on);
		wifi_image = (ImageView) findViewById(R.id.wifi_image);
		refresh = (Button) findViewById(R.id.wifi_refresh);
		wifipaired_device = (ListView) findViewById(R.id.wifipaired_device);
		status = (TextView) findViewById(R.id.wifi_status);
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled()) {
			wifi_On.setChecked(true);
			status.setText("Wifi Turned On");
		}

	}

}
