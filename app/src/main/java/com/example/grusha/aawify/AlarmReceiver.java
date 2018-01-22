package com.example.grusha.aawify;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by GRUSHA on 06-01-2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    String state;
    String t;
    Exception exception;
    URL url;
    String h;
    String f;
    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context,"I m runnings",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(context,NewUpdates.class);
        context.startService(i);
        /*ArrayList<Names>titles1=new ArrayList<Names>();
        ArrayList<Names>links1=new ArrayList<Names>();
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sim=new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat sim1=new SimpleDateFormat("HH:mm:ss");
        sim1.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time=sim1.format(cal.getTime());
        String date=sim.format(cal.getTime());
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(context);
        state = pref.getString(context.getString(R.string.list_key),context.getString(R.string.Delhi));
        switch (state){
            case "Mumbai":
                t="http://www.hindustantimes.com/rss/cities/mumbai/rssfeed.xml";
                break;

            case "Delhi":
                t="http://www.hindustantimes.com/rss/cities/delhi/rssfeed.xml";
                break;

            case "Dehradun":
                t="http://www.hindustantimes.com/rss/cities/dehradun/rssfeed.xml";
                break;
            case "Allahabad":
                t="https://timesofindia.indiatimes.com/rssfeeds/3947060.cms";
                break;
            case "Bhubaneswar":
                t="https://timesofindia.indiatimes.com/rssfeeds/4118235.cms";
                break;
            case "Coimbatore":
                t="https://timesofindia.indiatimes.com/rssfeeds/7503091.cms";
                break;
            case "Gurgaon":
                t="http://www.hindustantimes.com/rss/cities/gurgaon/rssfeed.xml";
                break;
            case "Guwahati":
                t="https://timesofindia.indiatimes.com/rssfeeds/4118215.cms";
                break;
            case "Hubli":
                t="https://timesofindia.indiatimes.com/rssfeeds/3942695.cms";
                break;
            case "Kanpur":
                t="https://timesofindia.indiatimes.com/rssfeeds/3947067.cms";
                break;
            case "Kolkata":
                t="http://www.hindustantimes.com/rss/cities/kolkata/rssfeed.xml";
                break;
            case "Ludhiana":
                t="https://timesofindia.indiatimes.com/rssfeeds/3947051.cms";
                break;
            case "Mangalore":
                t="https://timesofindia.indiatimes.com/rssfeeds/3942690.cms";
                break;
            case "Mysore":
                t="https://timesofindia.indiatimes.com/rssfeeds/3942693.cms";
                break;
            case "Noida":
                t="http://www.hindustantimes.com/rss/cities/noida/rssfeed.xml";
                break;
            case "Pune":
                t="https://timesofindia.indiatimes.com/rssfeeds/-2128821991.cms";
                break;
            case "Goa":
                t="https://timesofindia.indiatimes.com/rssfeeds/3012535.cms";
                break;
            case "Chandigarh":
                t="http://www.hindustantimes.com/rss/cities/chandigarh/rssfeed.xml";
                break;
            case "Lucknow":
                t="http://www.hindustantimes.com/rss/cities/lucknow/rssfeed.xml";
                break;
            case "Patna":
                t="https://timesofindia.indiatimes.com/rssfeeds/-2128817995.cms";
                break;
            case "Jaipur":
                t="https://timesofindia.indiatimes.com/rssfeeds/3012544.cms";
                break;
            case "Nagpur":
                t="https://timesofindia.indiatimes.com/rssfeeds/442002.cms";
                break;
            case "Rajkot":
                t="https://timesofindia.indiatimes.com/rssfeeds/3942663.cms";
                break;
            case "Ranchi":
                t="https://timesofindia.indiatimes.com/rssfeeds/4118245.cms";
                break;
            case "Surat":
                t="https://timesofindia.indiatimes.com/rssfeeds/3942660.cms";
                break;
            case "Vadodara":
                t="https://timesofindia.indiatimes.com/rssfeeds/3942666.cms";
                break;
            case "Varanasi":
                t="https://timesofindia.indiatimes.com/rssfeeds/3947071.cms";
                break;
            case "Thiruvananthapuram":
                t="https://timesofindia.indiatimes.com/rssfeeds/878156304.cms";
                break;
        }
        try{
            url=new URL(t);
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp=factory.newPullParser();
            xpp.setInput(getinputstream(url),"UTF_8");
            boolean insideitem=false;
            int event=xpp.getEventType();
            while(event!=XmlPullParser.END_DOCUMENT){
                if(event==XmlPullParser.START_TAG){
                    if(xpp.getName().equalsIgnoreCase("item")){
                        insideitem=true;
                    }
                    if(xpp.getName().equalsIgnoreCase("title")){
                        if(insideitem){
                            f=xpp.nextText();
                        }
                    }
                    if(xpp.getName().equalsIgnoreCase("pubDate")){
                        if(insideitem){
                            if (xpp.nextText().contains(date)){
                            Names d=new Names(f);
                            titles1.add(d);
                        }
                    }}
                    else if(xpp.getName().equalsIgnoreCase("link")){
                        if(insideitem){
                            Names g=new Names(xpp.nextText());
                            links1.add(g);
                        }
                    }
                }
                else if(event==XmlPullParser.END_TAG&&xpp.getName().equalsIgnoreCase("item")){
                    insideitem=false;
                }
                event=xpp.next();
            }
        }
        catch (MalformedURLException e){
            exception=e;
        }
        catch (XmlPullParserException e){
            exception=e;
        }
        catch (IOException e){
            exception=e;
        }
        StringBuilder sb=new StringBuilder();
        for(Names s:titles1){
            sb.append(s.getname());
            sb.append("\n");
        }
        h=sb.toString();
        NotificationCompat.Builder n=new NotificationCompat.Builder(context).setContentTitle("Notification").setContentText(date+" "+time+"\n"+h).setSmallIcon(R.mipmap.ic_launcher);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,n.build());*/
    }
   /* public InputStream getinputstream(URL url){
        try{
            return url.openConnection().getInputStream();
        }
        catch (IOException e){
            return null;
        }
    }*/
}
