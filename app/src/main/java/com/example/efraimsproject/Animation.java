package com.example.efraimsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class Animation extends AppCompatActivity {
    ImageView img;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        img = findViewById(R.id.imageView);

        AnimationDrawable animation = new AnimationDrawable();

        animation.addFrame(getDrawable(R.drawable.khabib),500);
        animation.addFrame(getDrawable(R.drawable.jordan),500);
        animation.addFrame(getDrawable(R.drawable.ronaldo),500);
        animation.addFrame(getDrawable(R.drawable.messi),500);
        animation.setOneShot(false);
        img.setBackground(animation);

        animation.start();
    }
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver, filter);
    }
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airplaneModeChangeReceiver);
    }
}