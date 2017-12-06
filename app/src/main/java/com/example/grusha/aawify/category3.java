package com.example.grusha.aawify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class category3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category3);
        ArrayList<Names> names3=new ArrayList<Names>();
        names3.add(new Names("Nation"));
        names3.add(new Names("World"));
        names3.add(new Names("Health"));
        names3.add(new Names("Sports"));
        NamesAdapter adapt1=new NamesAdapter(this,names3);
        ListView listview1= (ListView) findViewById(R.id.list3);
        listview1.setAdapter(adapt1);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent i=new Intent(category3.this,indianationrss.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent s=new Intent(category3.this,indiaworldrss.class);
                        startActivity(s);
                        break;
                    case 2:
                        Intent t=new Intent(category3.this,indiahealth.class);
                        startActivity(t);
                        break;
                    case 3:
                        Intent p=new Intent(category3.this,indiasports.class);
                        startActivity(p);
                        break;
                }
            }
        });
    }
}
