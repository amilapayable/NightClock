package com.aminogira.aminonightclock;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Check the current date format preference and update UI accordingly
        int dateFormat = sharedPreferences.getInt("dateFormat", 24);
        RadioButton radio24h = findViewById(R.id.radio24h);
        RadioButton radio12h = findViewById(R.id.radio12h);
        if (dateFormat == 24) {
            radio24h.setChecked(true);
        } else {
            radio12h.setChecked(true);
        }

        // Handle radio button clicks
        radio24h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    saveDateFormat(24);
                }
            }
        });

        radio12h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    saveDateFormat(12);
                }
            }
        });
    }

    private void saveDateFormat(int format) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("dateFormat", format);
        editor.apply();
    }
}
