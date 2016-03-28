package com.example.ngers.universal_locationmanager.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by ngers on 29.12.15.
 */
public class LocationServiceGPS extends LocationNameService implements LocationService {

    private LocationManager locationManager;

    private Location mLastLocation;
    private Context mContext;
    private static LocationServiceGPS instance;

    private LocationServiceGPS(Context context) {
        super(context);
        this.mContext = context;
        locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);


    }

    public static LocationServiceGPS getInstance(Context context) {
        if (instance == null)
            instance = new LocationServiceGPS(context);
        return instance;
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mLastLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            mLastLocation = locationManager.getLastKnownLocation(provider);
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public boolean isWork() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            return true;
        else
            return false;
    }

    @Override
    public String getLocationName() {
        return super.getLocationName(mLastLocation);
    }


    @Override
    public double getLat() {
        return mLastLocation.getLatitude();
    }

    @Override
    public double getLong() {
        return mLastLocation.getLongitude();
    }

    @Override
    public void onStart() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);
    }

    public void onStop() {
        locationManager.removeUpdates(locationListener);
    }


}
