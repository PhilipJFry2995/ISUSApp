package com.example.dfily.isusapp.location;

import android.content.Context;
import android.location.Location;

import com.example.dfily.isusapp.R;

import java.util.Locale;

/**
 * Created by dfily on 23.10.2017.
 */

public class LocationFormatter {

    public static String formatPoint(Context context, Location location){
        return String.format(Locale.US, context.getString(R.string.point_format), location.getLongitude(), location.getLatitude());
    }

}
