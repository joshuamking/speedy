package com.speedy.ui.race;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.speedy.R;
import com.speedy.app.NotificationController;
import com.speedy.app.Utils;

public class RaceActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
	private static final int MY_LOCATION_REQUEST_CODE = 23553;
	GoogleMap mMap;
	@BindView (R.id.searching_for_me_progress_bar) ProgressBar     searchingForMe;
	private                                        Location        location;
	private                                        LocationManager locationManager;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
							 WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
							 WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
							 WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

		setContentView(R.layout.activity_race);

		ButterKnife.bind(this);

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
		if (requestCode == MY_LOCATION_REQUEST_CODE) {
			if (permissions.length >= 1 && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				requestUpdates();
			}
			else {
				finish();
				Utils.toast(getApplicationContext(), "Okay, fine. Screw you.... \uD83D\uDE1D");
			}
		}
	}

	@Override
	public void onMapReady (GoogleMap googleMap) {
		mMap = googleMap;

		mMap.setBuildingsEnabled(false);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			requestUpdates();
		}
		else { requestPermissions(new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, MY_LOCATION_REQUEST_CODE); }
	}

	@Override
	public void onLocationChanged (Location location) {
		if (location == null) { return; }

		this.location = location;

		mMap.getUiSettings().setMyLocationButtonEnabled(false);
		LatLng target = new LatLng(location.getLatitude(), location.getLongitude());
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
																			   .target(target)
																			   .tilt(90)
																			   .zoom(1000000)
																			   .bearing(location.getBearing())
																			   .build()));
		searchingForMe.setVisibility(View.GONE);
		NotificationController.notifyProgress(getApplicationContext(), (int) (Math.random() * 100));
	}

	@Override
	public void onStatusChanged (String s, int i, Bundle bundle) {

	}

	@Override
	public void onProviderEnabled (String s) {

	}

	@Override
	public void onProviderDisabled (String s) {

	}

	@Override
	protected void onResume () {
		super.onResume();

		onLocationChanged(location);
	}

	@SuppressWarnings ("MissingPermission")
	private void requestUpdates () {
		mMap.setMyLocationEnabled(true);
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 1000, this);
	}
}
