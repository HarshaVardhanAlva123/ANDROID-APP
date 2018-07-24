package com.example.working;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.gowsik.R;

public class MusicPlayer extends Activity {
	ImageView songimage;
	Button play, forward, rewind;
	TextView playingTime, totalTime, songName, status;
	SeekBar seekBar;
	MediaPlayer mediaPlayer;
	int forwardTime = 10000, backwordTime = 10000;
	Handler myhandler = new Handler();
	boolean isPlaying = true;
	double starttime = 0, finaltime = 0;
	MediaMetadataRetriever medata;
	byte[] rawart;
	Bitmap art;
	BitmapFactory.Options bfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_palyer);
		initialaiz();

		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (isPlaying) {

					mediaPlayer.start();
					play.setBackgroundResource(R.drawable.pause);
					isPlaying = false;
					status.setText("Playing");
					myhandler.postDelayed(updatedSongTime,100);
				} else {
					mediaPlayer.pause();
					isPlaying = true;
					play.setBackgroundResource(R.drawable.play);
					status.setText("Pause");
				}

			}
		});

		forward.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				starttime = starttime + forwardTime;
				mediaPlayer.seekTo((int) starttime);

			}
		});

		rewind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				starttime = starttime - forwardTime;
				mediaPlayer.seekTo((int) starttime);
			}
		});

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				starttime = arg0.getProgress();
				mediaPlayer.seekTo((int) starttime);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void initialaiz() {
		// TODO Auto-generated method stub
		songimage = (ImageView) findViewById(R.id.song_image);
		play = (Button) findViewById(R.id.play_button);
		forward = (Button) findViewById(R.id.forward_button);
		rewind = (Button) findViewById(R.id.rewind_button);
		playingTime = (TextView) findViewById(R.id.start_time);
		totalTime = (TextView) findViewById(R.id.total_time);
		songName = (TextView) findViewById(R.id.song_name);
		seekBar = (SeekBar) findViewById(R.id.playing_seekbar);
		status = (TextView) findViewById(R.id.song_status);
		mediaPlayer = MediaPlayer.create(this, R.raw.nenjukulle);
		seekBar.setMax(mediaPlayer.getDuration());
		starttime = mediaPlayer.getCurrentPosition();
		finaltime = mediaPlayer.getDuration();
		songName.setText(mediaPlayer.getTrackInfo().toString());
		totalTime.setText(String.format(
				"%d:%d",
				TimeUnit.MILLISECONDS.toMinutes((long) finaltime),
				TimeUnit.MILLISECONDS.toSeconds((long) finaltime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes((long) finaltime))));
		medata = new MediaMetadataRetriever();
		bfo = new BitmapFactory.Options();
		Uri uri = Uri
				.parse("android.resource://com.example.gowsik/raw/nenjukulle");
		medata.setDataSource(getApplicationContext(), uri);
		rawart = medata.getEmbeddedPicture();
		if (null != rawart)
			art = BitmapFactory.decodeByteArray(rawart, 0, rawart.length, bfo);
		songimage.setImageBitmap(art);

	}

	private Runnable updatedSongTime = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			starttime = mediaPlayer.getCurrentPosition();
			playingTime.setText(String.format(
					"%d:%d",
					TimeUnit.MILLISECONDS.toMinutes((long) starttime),
					TimeUnit.MILLISECONDS.toSeconds((long) starttime)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
									.toMinutes((long) starttime))));
			seekBar.setProgress((int) starttime);
			myhandler.postDelayed(this, 100);

		}
	};

	protected void onPause() {
		super.onPause();
		mediaPlayer.stop();

	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putDouble("starttime", starttime);
		
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		double st=savedInstanceState.getDouble("starttime");
		mediaPlayer.seekTo((int)st);
		mediaPlayer.start();
		seekBar.setProgress((int)st);
	}

}
