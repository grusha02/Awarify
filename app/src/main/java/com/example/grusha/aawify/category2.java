package com.example.grusha.aawify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class category2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Hindustan Times");
        setContentView(R.layout.activity_category2);
        ArrayList<Names> names2=new ArrayList<Names>();
        names2.add(new Names("India"));
        names2.add(new Names("World"));
        names2.add(new Names("Sports"));
        names2.add(new Names("Entertainment"));
        names2.add(new Names("Health and Fitness"));
        names2.add(new Names("Education"));
        names2.add(new Names("Business"));
        NamesAdapter adapt1=new NamesAdapter(this,names2);
        ListView listview1= (ListView) findViewById(R.id.list2);
        listview1.setAdapter(adapt1);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent i=new Intent(category2.this,HindustanIndiarss.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent s=new Intent(category2.this,HindustanWorldrss.class);
                        startActivity(s);
                        break;
                    case 2:
                        Intent t=new Intent(category2.this,HindustanSportsrss.class);
                        startActivity(t);
                        break;
                    case 3:
                        Intent p=new Intent(category2.this,HindustanEntertainmentrss.class);
                        startActivity(p);
                        break;
                    case 4:
                        Intent q=new Intent(category2.this,HindustanHealthrss.class);
                        startActivity(q);
                        break;
                    case 5:
                        Intent u=new Intent(category2.this,HindustanEducationrss.class);
                        startActivity(u);
                        break;
                    case 6:
                        Intent k=new Intent(category2.this,HindustanBusinessrss.class);
                        startActivity(k);
                        break;
                }
            }
        });
    }
}
