package com.example.working;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gowsik.R;

public class Camera extends Activity {
	ImageView image;
	Button cam, save;
	Bitmap bit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		image = (ImageView) findViewById(R.id.image);
		cam = (Button) findViewById(R.id.camera_button);
		save = (Button) findViewById(R.id.save_button);
		save.setVisibility(View.GONE);
		cam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Intent camar = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(camar, 0);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.getMessage()
							.toString(), Toast.LENGTH_SHORT);
				}

			}
		});
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				StoreImage();

			}
		});
	}

	protected void StoreImage() {
		// TODO Auto-generated method stub
		File filename = new File("/sdcard/gowsik_Project/Captured_image/");
		filename.mkdirs();
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);

		File file = new File(filename, "Image-" + n + ".jpg");

		if (!(file.exists()))
			file.delete();
		try {
			FileOutputStream fos = new FileOutputStream(file);
			bit.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
			Toast.makeText(getApplicationContext(),
					"Image saved at location  " + file.toString(),
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.getMessage().toString(),
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				save.setVisibility(View.VISIBLE);
				cam.setText("Capture New");
				bit = (Bitmap) data.getExtras().get("data");
				image.setImageBitmap(bit);
			}
		}

	}

}
