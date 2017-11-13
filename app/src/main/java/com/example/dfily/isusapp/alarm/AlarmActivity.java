package com.example.dfily.isusapp.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dfily.isusapp.R;

public class AlarmActivity extends AppCompatActivity {

    AlarmManager alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent intent = new Intent();
        intent.setAction(AlarmEventReceiver.ACTION);
        sendBroadcast(intent);
    }

    public void startAlarm(View view) {
        alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmEventReceiver.class);
        intent.setAction(AlarmEventReceiver.ACTION);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmEventReceiver.ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarm != null) {
            /*
            ELAPSED_REALTIME - отсчет времени от загрузки телефона
            ELAPSED_REALTIME_WAKEUP - событие совершится даже если экран выключен
            RTC - отсчет времени по UTC, для событий, например, раз в день
            RTC_WAKEUP - при выключеном экране
             */
            /*
            alarm.setRepeating()
            alarm.setInexactRepeating() ОС синхронизирует события разных приложений для экономии батареи
             */
            alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, 60000, pIntent);
        }
        Log.d("mylogs", "Менеджер запущен");
    }

    public void stopAlarm(View view) {
        if (alarm != null) {
            Intent intent = new Intent(this, AlarmEventReceiver.class);
            intent.setAction(AlarmEventReceiver.ACTION);
            PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmEventReceiver.ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarm.cancel(pIntent);
        }
        Log.d("mylogs", "Менеджер остановлен");
    }

}
