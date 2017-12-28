package com.example.grusha.aawify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Settings extends AppCompatActivity{

   /* public  SharedPreferences shared;
    public  SharedPreferences.Editor edit;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction()
                .add(R.id.contain, new SettingsFragment(),"settings_tag")
                .commit();

    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_screen);
            ListPreference listpref=(ListPreference) findPreference("list_preference");
            Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    Intent i=new Intent(getActivity(),papernameActivity.class);
                    startActivity(i);
                   /* i.putExtra("Data",newValue.toString());
                    setResult(RESULT_OK,i);
                    startActivity(i);*/
                    return true;
                }
            };

            listpref.setOnPreferenceChangeListener(listener);

        }
    }
}
