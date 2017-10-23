package com.example.dfily.isusapp.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dfily.isusapp.R;

public class LocationActivityExample extends AppCompatActivity implements MyLocationListener {

    private int REQUEST_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // Запрашиваем разрешения на геолокацию
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ID);
            return;
        }

        LocationSingleton singleton = LocationSingleton.getInstance(this);
        singleton.registerListener(this);

        LocationLoggerExample locationLoggerExample = new LocationLoggerExample(this);
    }

    @Override
    protected void onDestroy() {
        LocationSingleton singleton = LocationSingleton.getInstance(this);
        singleton.unregisterListener(this);
        super.onDestroy();
    }

    public void closeActivity(View view) {
        finish();
    }

    /**
     * Обрабатываем полученные разрешения
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    @Override
    public void updateLocation(Location location) {
        TextView textView = (TextView) findViewById(R.id.location);
        textView.setText(LocationFormatter.formatPoint(this, location));
    }
}
