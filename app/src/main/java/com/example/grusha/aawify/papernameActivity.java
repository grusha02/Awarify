package com.example.grusha.aawify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class papernameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Papers");
        setContentView(R.layout.activity_papername);

        ArrayList<Names> names = new ArrayList<Names>();
        names.add(new Names("Times Of India"));
        names.add(new Names("Hindustan Times"));
        names.add(new Names("India Today"));
        NamesAdapter adapt = new NamesAdapter(this, names);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapt);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent firstIntent = new Intent(papernameActivity.this, category1.class);
                        startActivity(firstIntent);
                        break;
                    case 1:
                        Intent secondIntent = new Intent(papernameActivity.this, category2.class);
                        startActivity(secondIntent);
                        break;
                    case 2:
                        Intent thirdIntent = new Intent(papernameActivity.this, category3.class);
                        startActivity(thirdIntent);
                        break;
                }
            }
        });
    }}