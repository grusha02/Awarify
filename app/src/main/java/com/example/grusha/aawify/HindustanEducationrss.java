package com.example.grusha.aawify;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HindustanEducationrss extends AppCompatActivity {

    ListView lv;
    ArrayList<String> titles12;
    ArrayList<String> links12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hindustan_educationrss);

        lv=(ListView) findViewById(R.id.listvw12);
        titles12=new ArrayList<String>();
        links12=new ArrayList<String>();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri=Uri.parse(links12.get(position));
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        new HindustanEducationrss.ProcessInBackground().execute();
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

        ProgressDialog progressDialog=new ProgressDialog(HindustanEducationrss.this);
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
                URL url=new URL("http://www.hindustantimes.com/rss/education/rssfeed.xml");
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
                                titles12.add(xpp.nextText());
                            }
                        }
                        else if(xpp.getName().equalsIgnoreCase("link")){
                            if(insideitem){
                                links12.add(xpp.nextText());
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
            ArrayAdapter<String> adapt=new ArrayAdapter<String>(HindustanEducationrss.this,android.R.layout.simple_list_item_1,titles12);
            lv.setAdapter(adapt);
            progressDialog.dismiss();
        }
    }
}
