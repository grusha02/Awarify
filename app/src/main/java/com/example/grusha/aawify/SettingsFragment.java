package com.example.grusha.aawify;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grusha.aawify.papernameActivity;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.grusha.aawify.R.array.lists;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    ListView lv;
    ArrayList<Names> titles1;
    ArrayList<Names> links1;
    SharedPreferences shared;
    String t;
    String state;
    URL url;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootview=inflater.inflate(R.layout.activity_papername,container,false);
        lv=(ListView) rootview.findViewById(R.id.list);
        titles1=new ArrayList<Names>();
        links1=new ArrayList<Names>();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri=Uri.parse(links1.get(position).getname());
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        return rootview;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        state = pref.getString(getString(R.string.list_key),getString(R.string.Delhi));
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
        new ProcessInBackground().execute();
    }
    public InputStream getinputstream(URL url){
        try{
            return url.openConnection().getInputStream();
        }
        catch (IOException e){
            return null;
        }
    }

    /*@Override
    public void onResume() {
        super.onResume();
        new ProcessInBackground().execute();
    }*/

    public class ProcessInBackground extends AsyncTask<Integer,Integer, Exception> {

        ProgressDialog progressDialog=new ProgressDialog(getActivity());
        Exception exception;

        @Override
        protected void onPreExecute() {
           super.onPreExecute();
            progressDialog.setMessage("Loading....");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... params) {
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
                        else if(xpp.getName().equalsIgnoreCase("title")){
                            if(insideitem){
                                Names d=new Names(xpp.nextText());
                                titles1.add(d);
                            }
                        }
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


            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            NamesAdapter adapt=new NamesAdapter(getActivity(),titles1);
            lv.setAdapter(adapt);
            progressDialog.dismiss();
        }
    }


}
