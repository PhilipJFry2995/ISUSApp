package com.example.dfily.isusapp.service;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dfily.isusapp.R;
import com.example.dfily.isusapp.location.LocationFormatter;
import com.example.dfily.isusapp.location.LocationSingleton;
import com.example.dfily.isusapp.location.MyLocationListener;

public class ServiceActivity extends AppCompatActivity implements MyLocationListener {

    private int REQUEST_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // Запрашиваем разрешения на геолокацию
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ID);
            return;
        }

        Log.d("mylogs", "Разерешения имеются");

        LocationSingleton.getInstance(this).registerListener(this);
    }

    @Override
    protected void onDestroy() {
        LocationSingleton singleton = LocationSingleton.getInstance(this);
        singleton.unregisterListener(this);
        super.onDestroy();
    }

    //region Получение разрешений
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ID) {
            if (grantResults.length == 2
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                LocationSingleton singleton = LocationSingleton.getInstance(this);
                singleton.registerListener(this);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    //endregion

    public void startServiceClick(View view){
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);
    }

    public void stopServiceClick(View view){
        Intent intent = new Intent(this, LocationService.class);
        stopService(intent);
    }

    @Override
    public void updateLocation(Location location) {
        Log.d("mylogs", "Activity: " + LocationFormatter.formatLocation(this, location));
    }
}
