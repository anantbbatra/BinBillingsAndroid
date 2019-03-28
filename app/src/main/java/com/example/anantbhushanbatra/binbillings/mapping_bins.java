package com.example.anantbhushanbatra.binbillings;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mapping_bins extends AppCompatActivity implements OnMapReadyCallback {

    Random r = new Random();
    HttpManager httpManager;
    private GoogleMap mMap;
    ArrayList<Bin> nearBins;
    Location lastUserLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private final static int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 1;
    Call<ArrayList<Bin>> binHttpQuery;

    GoogleApiClient mGoogleApiClient;
    Marker mLocationMarker;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Bin Billings");
        setContentView(R.layout.activity_mapping_bins);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        httpManager = new HttpManager();

    }

    public void getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);

        }else {
            mMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object

                                lastUserLocation = location;
                                populateMap();
                            }
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastKnownLocation();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLastKnownLocation();
    }

    //makes an api call to find nearby bins in a 5 km radius and creates markers on the map
    public void populateMap(){
        binHttpQuery = httpManager.getNearbyBins(lastUserLocation);

        binHttpQuery.enqueue(new Callback<ArrayList<Bin>>() {
            @Override
            public void onResponse(Call<ArrayList<Bin>> call, Response<ArrayList<Bin>> response) {
                nearBins = response.body();
                LatLng temp;
                String colorLabel;
                for (Bin bin: nearBins) {
                    temp = new LatLng(bin.getXCoordinate(), bin.getYCoordinate());
                    float markerAngle = -90 + r.nextFloat() * (180);
                    colorLabel = bin.getColor().substring(0, 1).toUpperCase() + bin.getColor().substring(1);
                    mMap.addMarker(new MarkerOptions().position(temp).rotation(markerAngle).title(colorLabel));
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Bin>> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });

        LatLng user = new LatLng(lastUserLocation.getLatitude(), lastUserLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(user).title("Your Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user,15));
    }
}


