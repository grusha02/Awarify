package com.example.grusha.aawify;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;




/**
 * Created by GRUSHA on 06-01-2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, NewUpdates.class);
        context.startService(i);

    }

}
