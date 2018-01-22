package com.example.grusha.aawify;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by GRUSHA on 06-01-2018.
 */

public class NewUpdates extends IntentService {
    String state;
    String t;
    Exception exception;
    URL url;
    String h;
    String f;
    String j;
    Date time1;
    InputStream input;
    int i=0;

    public NewUpdates(){
        super("NewUpdates");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ArrayList<Names>titles1=new ArrayList<Names>();
        ArrayList<Names>links1=new ArrayList<Names>();
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sim=new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat sim1=new SimpleDateFormat("HH:mm:ss");
        sim1.setTimeZone(TimeZone.getTimeZone("GMT"));
        SharedPreferences share=getSharedPreferences("timeinfo", Context.MODE_PRIVATE);
        cal.add(Calendar.HOUR,-share.getInt("time",1));
        String time=sim1.format(cal.getTime());
        String date=sim.format(cal.getTime());
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(this);
        state = pref.getString(this.getString(R.string.list_key),this.getString(R.string.Delhi));
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
            input=getinputstream(url);
            if(input==null){
                SharedPreferences shared=getSharedPreferences("timeinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=shared.edit();
                edit.putInt("time",shared.getInt("time",0)+1);
                edit.apply();
                return;
            }
            xpp.setInput(input,"UTF_8");
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
                            String l=xpp.nextText();
                            Pattern pat=Pattern.compile("([0-1][0-9]:[0-9][0-9]:[0-9][0-9])");
                            Matcher match=pat.matcher(l);
                            if(match.find()){
                                j=match.group(1);
                            }
                            try{
                            if (l.contains(date)&&sim1.parse(j).after(sim1.parse(time))){
                                Names d=new Names(f);
                                titles1.add(d);}}


                            catch(ParseException e){

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
        if(titles1.size()>0){
            Intent in=new Intent(this,papernameActivity.class);
            PendingIntent pend=PendingIntent.getActivity(this,0,in,0);
        NotificationCompat.Builder n=new NotificationCompat.Builder(this).setContentTitle(state).setContentText(h).setSmallIcon(R.mipmap.ic_launcher).setStyle(new NotificationCompat.BigTextStyle().bigText(h));
        n.setContentIntent(pend);
            NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,n.build());}
        SharedPreferences shared=getSharedPreferences("timeinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=shared.edit();
        edit.putInt("time",1);
        edit.apply();}

    public InputStream getinputstream(URL url){
        try{
            return url.openConnection().getInputStream();
        }
        catch (IOException e){

            return null;
        }
    }
    }

