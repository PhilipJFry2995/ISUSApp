package com.example.dfily.isusapp.location;

import android.content.Context;
import android.location.Location;
import android.util.Log;

/**
 * Created by dfily on 23.10.2017.
 */

public class LocationLoggerExample implements MyLocationListener {

    Context context;

    public LocationLoggerExample(Context context) {
        this.context = context;
    }

    @Override
    public void updateLocation(Location location) {
        Log.d("mylogs", LocationFormatter.formatLocation(context, location));
    }

}
