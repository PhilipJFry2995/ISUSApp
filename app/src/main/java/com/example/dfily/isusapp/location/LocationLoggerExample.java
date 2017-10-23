package com.example.dfily.isusapp.location;

import android.content.Context;
import android.location.Location;
import android.util.Log;

/**
 * Created by dfily on 23.10.2017.
 */

public class LocationLoggerExample implements MyLocationListener {

    private Context context;

    public LocationLoggerExample(Context context) {
        this.context = context;

        LocationSingleton singleton = LocationSingleton.getInstance(context);
        singleton.registerListener(this);
    }

    @Override
    public void updateLocation(Location location) {
        Log.d("isus_app", LocationFormatter.formatPoint(context, location));
    }
}
