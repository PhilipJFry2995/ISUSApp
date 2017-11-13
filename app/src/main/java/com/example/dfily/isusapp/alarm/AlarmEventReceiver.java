package com.example.dfily.isusapp.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by dfily on 13.11.2017.
 */

public class AlarmEventReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.example.dfily.isusapp.alarm_event";
    public static final int ID = 123;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null && intent.getAction().equals(ACTION)){
            Log.d("mylogs", "За работу");
        }
    }

}
