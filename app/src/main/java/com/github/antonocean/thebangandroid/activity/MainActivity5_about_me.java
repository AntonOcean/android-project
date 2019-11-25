package com.github.antonocean.thebangandroid.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.antonocean.thebangandroid.R;


public class MainActivity5_about_me extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        setTitle("About Me");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
