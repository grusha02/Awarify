package com.example.grusha.aawify;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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
                SharedPreferences pref = PreferenceManager
                        .getDefaultSharedPreferences(getActivity());
                String state = pref.getString("list_preference", "");

                URL url;
                String t;
                if(state=="Mumbai"){
                    t="https://timesofindia.indiatimes.com/rssfeeds/-2128838597.cms";
                }
                else if(state=="Delhi"){
                    t="https://timesofindia.indiatimes.com/rssfeeds/-2128839596.cms";
                }
                else if(state=="Bangalore"){
                    t="https://timesofindia.indiatimes.com/rssfeeds/-2128833038.cms";
                }
                else{
                    t="https://timesofindia.indiatimes.com/rssfeeds/-2128816011.cms";
                }
                //Toast.makeText(getActivity(), "THE VALUE IS "+state, Toast.LENGTH_SHORT).show();
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
