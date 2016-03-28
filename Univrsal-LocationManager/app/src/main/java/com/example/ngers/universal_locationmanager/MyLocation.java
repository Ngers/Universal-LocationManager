package com.example.ngers.universal_locationmanager;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ngers.universal_locationmanager.utils.LocationServiceGPS;
import com.example.ngers.universal_locationmanager.utils.LocationServiceGoogleApi;

public class MyLocation extends AppCompatActivity {

    private LocationServiceGoogleApi locationServesGoogleApi;
    private LocationServiceGPS locationServesGPS;
    private TextView tvLat;
    private TextView tvLong;
    private TextView tvMyLocation;
    private Button bGoogleApi;
    private Button bGPS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvLat = (TextView) findViewById(R.id.tvLatValue);
        tvLong = (TextView) findViewById(R.id.tvLongValue);
        tvMyLocation = (TextView) findViewById(R.id.tvLocationValue);
        bGoogleApi = (Button) findViewById(R.id.bGoogleApi);
        bGPS = (Button) findViewById(R.id.bGPS);

        bGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tvLat.setText(String.valueOf(locationServesGPS.getLat()));
                    tvLong.setText(String.valueOf(locationServesGPS.getLong()));
                    tvMyLocation.setText(locationServesGPS.getLocationName());
                    Snackbar.make(view, "GPS", Snackbar.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    setError(view);
                }
            }
        });
        bGoogleApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tvLat.setText(String.valueOf(locationServesGoogleApi.getLat()));
                    tvLong.setText(String.valueOf(locationServesGoogleApi.getLong()));
                    tvMyLocation.setText(locationServesGoogleApi.getLocationName());
                    Snackbar.make(view, "GoogleApi", Snackbar.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    setError(view);
                }
            }
        });
    }

    private void setError(View view) {
        tvLat.setText("");
        tvLong.setText("");
        tvMyLocation.setText(getResources().getString(R.string.location_error_define));
        Snackbar.make(view, "Service is not ready", Snackbar.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        locationServesGoogleApi = LocationServiceGoogleApi.getInstance(this);
        locationServesGoogleApi.onStart();

        locationServesGPS = LocationServiceGPS.getInstance(this);
        locationServesGPS.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationServesGoogleApi.onStop();
        locationServesGPS.onStop();
    }


}
