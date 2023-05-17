package com.example.efraimsproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {
    ListView recyclerView;
    DataBase2 myDB;
    private ArrayList<Products> productsArrayList;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        myDB = new DataBase2(this);
        recyclerView = findViewById(R.id.recyclerView);
        productsArrayList = new ArrayList<>();
        productsArrayList = myDB.getAllRecipes();
        CustomAdapter adapter = new CustomAdapter(this , productsArrayList);
        recyclerView.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.n1)
        {
            Toast.makeText(this,"Like to see you again.",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(this,Main2.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.n2)
        {
            Toast.makeText(this,"Lets shopping right now.",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(this,Shop.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.n3)
        {
            Toast.makeText(this,"What do think about my project? Rate us.",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(this, AboutUs.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.n4)
        {
            Toast.makeText(this,"Now will see what is the temperature",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(this, Temperature.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"It will be legendary",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(this, Animation.class);
            startActivity(intent);
        }
        return true;
    }
}