package com.example.grusha.aawify;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Timesbuss extends AppCompatActivity {
    ListView lv;
    ArrayList<Names> titles1;
    ArrayList<Names> links1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesbuss);
        getSupportActionBar().setTitle("Business");
        lv=(ListView) findViewById(R.id.listvw22);
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
        new Timesbuss.ProcessInBackground().execute();
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

        ProgressDialog progressDialog=new ProgressDialog(Timesbuss.this);
        Exception exception;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading....");
            progressDialog.show();
        }

        @Override
        protected  Exception doInBackground(Integer... params) {
            try{
                URL url=new URL("https://timesofindia.indiatimes.com/rssfeeds/1898055.cms");
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
            NamesAdapter adapt=new NamesAdapter(Timesbuss.this,titles1);
            lv.setAdapter(adapt);
            progressDialog.dismiss();
        }
    }
}
