package com.example.grusha.aawify;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private PendingIntent pendingintent;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingintent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        long interval=1000;
        long curr=System.currentTimeMillis();

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, curr, interval, pendingintent);
    }
    public void movetopaper(View view){
        ConnectivityManager c=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo n=c.getActiveNetworkInfo();
        boolean isconnected=n!=null&&n.isConnectedOrConnecting();
        if(!isconnected){
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
        else if(isconnected){
        Intent i=new Intent(this,papernameActivity.class);
        startActivity(i);}
    }

}
