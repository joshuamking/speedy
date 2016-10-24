package com.speedy.wear;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.ProgressBar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import me.denley.courier.Courier;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class InRaceActivity extends WearableActivity
		implements DataApi.DataListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
	private static final SimpleDateFormat AMBIENT_DATE_FORMAT = new SimpleDateFormat("HH:mm", Locale.US);
	private final        String           TAG                 = "TAG";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_in_race);
		setAmbientEnabled();

		//		mContainerView = (BoxInsetLayout) findViewById(R.id.container);
		//		mTextView = (TextView) findViewById(R.id.text);
		//		mClockView = (TextView) findViewById(R.id.clock);

		//		Courier.startReceiving(this);

		//		Courier.deliverData(this, "/player", DataStore.getPlayer());
		//		Courier.deliverData(this, "/game-options", DataStore.getPlayer());

		final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
		new CountDownTimer(0, 100) {
			@Override
			public void onTick (final long l) {
				runOnUiThread(new Runnable() {
					@Override
					public void run () {
						if (l <= 100) {
							progress.setProgress((int) l);
						}
					}
				});
			}

			@Override
			public void onFinish () {

			}
		}.start();
	}

	@Override
	public void onConnected (Bundle connectionHint) {
		Log.d(TAG, "onConnected: " + connectionHint);
		// Now you can use the Data Layer API
	}

	@Override
	public void onConnectionSuspended (int cause) {
		Log.d(TAG, "onConnectionSuspended: " + cause);
	}

	@Override
	public void onConnectionFailed (ConnectionResult result) {
		Log.d(TAG, "onConnectionFailed: " + result);
	}

	@Override
	public void onEnterAmbient (Bundle ambientDetails) {
		super.onEnterAmbient(ambientDetails);
		updateDisplay();
	}

	@Override
	public void onUpdateAmbient () {
		super.onUpdateAmbient();
		updateDisplay();
	}

	@Override
	public void onExitAmbient () {
		updateDisplay();
		super.onExitAmbient();
	}

	@Override
	public void onDataChanged (DataEventBuffer dataEventBuffer) {

	}

	@Override
	protected void onDestroy () {
		super.onDestroy();
		Courier.stopReceiving(this);
	}

	private void updateDisplay () {
		//		if (isAmbient()) {
		//			mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
		//			mTextView.setTextColor(getResources().getColor(android.R.color.white));
		//			mClockView.setVisibility(View.VISIBLE);
		//
		//			mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
		//		}
		//		else {
		//			mContainerView.setBackground(null);
		//			mTextView.setTextColor(getResources().getColor(android.R.color.black));
		//			mClockView.setVisibility(View.GONE);
		//		}
	}
}
