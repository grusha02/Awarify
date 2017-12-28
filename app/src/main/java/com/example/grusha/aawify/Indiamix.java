package com.example.grusha.aawify;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class Indiamix extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public ActionBarDrawerToggle mt;
    public DrawerLayout m;
    public NavigationView navigationView;

    ListView lv;
    ArrayList<String> allinks=new ArrayList<String>();
    ArrayList<Names> titles;
    ArrayList<Names> links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Indian Express");
        setContentView(R.layout.activity_indiamix);
        m=(DrawerLayout)findViewById(R.id.drawss);
        mt=new ActionBarDrawerToggle(this,m,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        m.addDrawerListener(mt);
        mt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.draw3);
        navigationView.setNavigationItemSelectedListener(Indiamix.this);
        lv=(ListView) findViewById(R.id.listvw21);
        allinks.add("http://indianexpress.com/section/world/feed/");
        allinks.add("http://indianexpress.com/section/sports/feed/");
        allinks.add("http://indianexpress.com/section/india/feed/");
        allinks.add("http://indianexpress.com/section/lifestyle/health/feed/");
        titles=new ArrayList<Names>();
        links=new ArrayList<Names>();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri=Uri.parse(links.get(position).getname());
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        new Indiamix.ProcessInBackground().execute();
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id =item.getItemId();

        if(id==R.id.worldi){
            Intent i=new Intent(this,indiaworldrss.class);
            startActivity(i);}
        else if(id==R.id.nation){
            Intent j=new Intent(this,indianationrss.class);
            startActivity(j);}
        else if(id== R.id.healthi){
            Intent u=new Intent(this,indiahealth.class);
            startActivity(u);}
        else{
            Intent x=new Intent(this,indiasports.class);
            startActivity(x);}
        // m.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mt.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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

        ProgressDialog progressDialog=new ProgressDialog(Indiamix.this);
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
                for(String s:allinks){
                    URL url=new URL(s);
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
                                    titles.add(d);
                                }
                            }
                            else if(xpp.getName().equalsIgnoreCase("link")){
                                if(insideitem){
                                    Names c=new Names(xpp.nextText());
                                    links.add(c);
                                }
                            }
                        }
                        else if(event==XmlPullParser.END_TAG&&xpp.getName().equalsIgnoreCase("item")){
                            insideitem=false;
                        }
                        event=xpp.next();}
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
            NamesAdapter adapt=new NamesAdapter(Indiamix.this,titles);
            lv.setAdapter(adapt);
            progressDialog.dismiss();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.settings_menu,menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchview=(SearchView) menu.findItem(R.id.search).getActionView();
        searchview.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Names> m=new ArrayList<Names>();
                if(newText.length()!=0){
                    for(Names n:titles){
                        if(n.getname().toUpperCase().contains(newText.toUpperCase())){
                            m.add(n);
                        }
                    }
                    lv.setAdapter(new NamesAdapter(Indiamix.this,m));
                }

                return true;
            }
        });
        return true;
    }

}
