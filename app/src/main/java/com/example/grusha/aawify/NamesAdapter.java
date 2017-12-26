package com.example.grusha.aawify;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by GRUSHA on 04-12-2017.
 */

public class NamesAdapter extends ArrayAdapter<Names> {
    public NamesAdapter(Context context, List<Names> objects) {
        super(context,0,objects);
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View listview=convertView;
        if(listview==null){
            listview= LayoutInflater.from(getContext()).inflate(R.layout.paper_view,parent,false);
        }
        Names current=getItem(position);
        TextView text=(TextView)listview.findViewById(R.id.name);
        text.setText(current.getname());
        return listview;
    }
    }

