package com.example.helloworld.location;

import com.example.helloworld.R;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GetLocationActivity extends Activity {
	//纬度
	private double latitude=0.0;
	//经度
	private double longitude =0.0;
	
	private Button getLocationBtn;
	private TextView showLocation;
	
	// The minimum distance to change Updates in meters
    public final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    public final long MIN_TIME_BW_UPDATES = 1; // 1 minute
    
    private String LOCATION_TAG = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location);
		
		getLocationBtn = (Button)findViewById(R.id.get_location);
		
		showLocation = (TextView)findViewById(R.id.show_location);
		
		getLocationBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getLocation();
			}
		});
	}

	public void getLocation(){
		LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		boolean isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if(isGPS){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, mLocationListener);
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(location != null){
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				LOCATION_TAG = "GPS";
				showLocation.setText("GPS + 经度是：" + longitude + " 纬度是：" + latitude);
			}
		}else{
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES,mLocationListener);   
			Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);   
			if(location != null){   
				latitude = location.getLatitude(); //纬度
				longitude = location.getLongitude(); //经度   
				LOCATION_TAG = "NETWORK";
				showLocation.setText("NETWORK + 经度是：" + longitude + " 纬度是：" + latitude);
			}   
		}
	}
	
	private final LocationListener mLocationListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			showLocation.setText(LOCATION_TAG + " + 经度是：" + longitude + " 纬度是：" + latitude);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			
		}
	};
}
