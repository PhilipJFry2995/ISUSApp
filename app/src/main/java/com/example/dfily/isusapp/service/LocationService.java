package com.example.dfily.isusapp.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dfily.isusapp.location.LocationFormatter;
import com.example.dfily.isusapp.location.LocationSingleton;
import com.example.dfily.isusapp.location.MyLocationListener;

/**
 * Created by dfily on 11.11.2017.
 */

public class LocationService extends Service implements MyLocationListener {


    /*
    Вызывается при вызове метода startService, если сервис не был запущен
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("mylogs", "Сервис создан");
        // Подписываемся на обновление координат
        LocationSingleton locationSingleton = LocationSingleton.getInstance(this);
        locationSingleton.registerListener(this);
    }

    /*
    Вызывается после метода onCreate()
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("mylogs", "Сервис запущен");
        /*
        START_STICKY сервис остановится только если вызвать метод stopService или stopSelf
        START_NOT_STICKY сервис будет остановлен после того как выполнит свою задачу
        другие константы https://developer.android.com/reference/android/app/Service.html
         */
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("mylogs", "Сервис уничтожен");
        LocationSingleton.getInstance(this).unregisterListener(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void updateLocation(Location location) {
        Log.d("mylogs", "Service: " + LocationFormatter.formatLocation(this, location));
    }

}
