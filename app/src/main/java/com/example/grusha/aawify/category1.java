package com.example.grusha.aawify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class category1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);
        ArrayList<Names> names1=new ArrayList<Names>();
        names1.add(new Names("Business"));
        names1.add(new Names("World"));
        names1.add(new Names("Entertainment"));
        names1.add(new Names("Sports"));
        names1.add(new Names("Technology"));
        names1.add(new Names("Education"));
        NamesAdapter adapt1=new NamesAdapter(this,names1);
        ListView listview1= (ListView) findViewById(R.id.list1);
        listview1.setAdapter(adapt1);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent i=new Intent(category1.this,TimesBussrss.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent s=new Intent(category1.this,TimesWorldrss.class);
                        startActivity(s);
                        break;
                    case 2:
                        Intent t=new Intent(category1.this,TimesEntertainmentrss.class);
                        startActivity(t);
                        break;
                    case 3:
                        Intent p=new Intent(category1.this,TimesSportsrss.class);
                        startActivity(p);
                        break;
                    case 4:
                        Intent q=new Intent(category1.this,TimesTechrss.class);
                        startActivity(q);
                        break;
                    case 5:
                        Intent u=new Intent(category1.this,TimesEducationrss.class);
                        startActivity(u);
                }
            }
        });
    }
}
