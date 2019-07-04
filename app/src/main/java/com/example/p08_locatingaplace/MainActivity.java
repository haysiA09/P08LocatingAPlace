package com.example.p08_locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button north, central, east;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        north = findViewById(R.id.button1);
        central = findViewById(R.id.button2);
        east = findViewById(R.id.button3);


        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                north.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng poi_North = new LatLng(1.424450, 103.829849);
                        Marker cp = map.addMarker(new
                                MarkerOptions()
                                .position(poi_North)
                                .title("HQ-North")
                                .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm,Tel:65433456")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
                        float zoomLevel = 16.0f; //This goes up to 21
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North, zoomLevel));

                    }
                });

                central.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng poi_Central = new LatLng(1.289440, 103.849980);
                        Marker cp = map.addMarker(new
                                MarkerOptions()
                                .position(poi_Central)
                                .title("Central")
                                .snippet("Block 3A, Orchard Ave 3, 134542, Operating hours: 11am-8pm, Tel:67788652")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                        float zoomLevel = 16.0f; //This goes up to 21
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central, zoomLevel));

                    }
                });

                east.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng poi_east = new LatLng(1.363110, 103.962130);
                        Marker cp = map.addMarker(new
                                MarkerOptions()
                                .position(poi_east)
                                .title("East")
                                .snippet("Block 555, Tampines Ave 3, 287788, Operating hours: 9am-5pm, Tel:66776677")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        float zoomLevel = 16.0f; //This goes up to 21
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, zoomLevel));

                    }
                });

                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);


                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }


            }
        });


    }
}
