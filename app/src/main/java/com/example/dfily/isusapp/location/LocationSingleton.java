package com.example.dfily.isusapp.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dfily on 23.10.2017.
 */

public class LocationSingleton implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static LocationSingleton instance;
    private static Context mContext;
    private GoogleApiClient mGoogleApiClient;
    private List<MyLocationListener> listeners;
    private Location lastLocation;

    // Параметры частоты обновления
    private long interval = 10000;
    private long fastestInterval = 5000;
    private int priority = LocationRequest.PRIORITY_HIGH_ACCURACY;

    public static LocationSingleton getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new LocationSingleton();
        }
        return instance;
    }

    private LocationSingleton() {
        listeners = new ArrayList<>();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    public void updateLocationRequest(LocationRequest locationRequest) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("isus_app", "Недостаточно разрешений");
            return;
        }

        interval = locationRequest.getInterval();
        fastestInterval = locationRequest.getFastestInterval();
        priority = locationRequest.getPriority();

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
        Log.d("isus_app", "Обновлены параметры");
    }

    public void registerListener(MyLocationListener... listeners) {
        Log.d("isus_app", "Слушатели зарегистрированы");
        if (lastLocation != null) {
            for (MyLocationListener listener : listeners) {
                listener.updateLocation(lastLocation);
            }
        }
        this.listeners.addAll(Arrays.asList(listeners));
    }

    public void unregisterListener(MyLocationListener... listeners) {
        Log.d("isus_app", "Слушатели удалены");
        this.listeners.removeAll(Arrays.asList(listeners));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("isus_app", "Подключение установлено");
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("isus_app", "Недостаточно разрешений");
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (lastLocation != null) {
            for (MyLocationListener listener : listeners) {
                listener.updateLocation(lastLocation);
            }
        } else {
            Log.e("isus_app", "Нет данных о последней локации");
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(interval);
        mLocationRequest.setFastestInterval(fastestInterval);
        mLocationRequest.setPriority(priority);

        updateLocationRequest(mLocationRequest);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("isus_app", "ConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("isus_app", "ConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("isus_app", "Получено новое местоположение");
        lastLocation = location;
        if (lastLocation != null) {
            for (MyLocationListener listener : listeners) {
                listener.updateLocation(lastLocation);
            }
        }
    }
}
