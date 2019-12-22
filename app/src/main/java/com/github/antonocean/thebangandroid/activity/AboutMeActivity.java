package com.github.antonocean.thebangandroid.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.antonocean.thebangandroid.R;

import java.util.Objects;


public class AboutMeActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        setTitle("About Me");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }
}
