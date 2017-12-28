package com.example.grusha.aawify;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PapersFragment extends Fragment {
    public NamesAdapter adapt;
    public ArrayList<Names> names;
    public ListView list;


    public PapersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.activity_papername,container,false);
        names = new ArrayList<Names>();
        names.add(new Names("Times Of India"));
        names.add(new Names("Hindustan Times"));
        names.add(new Names("India Today"));
        adapt = new NamesAdapter(getActivity(), names);
        list = (ListView) rootview.findViewById(R.id.list);
        list.setAdapter(adapt);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent firstIntent = new Intent(getActivity(), Timesmix.class);
                        startActivity(firstIntent);
                        break;
                    case 1:
                        Intent secondIntent = new Intent(getActivity(), Hindustanmix.class);
                        startActivity(secondIntent);
                        break;
                    case 2:
                        Intent thirdIntent = new Intent(getActivity(), Indiamix.class);
                        startActivity(thirdIntent);
                        break;
                }
            }
        });
        return rootview;
    }

}
