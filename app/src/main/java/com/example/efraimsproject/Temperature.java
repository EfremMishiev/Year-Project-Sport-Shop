package com.example.efraimsproject;

import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Temperature extends AppCompatActivity implements SensorEventListener{


    private TextView tvTemp;
    private SensorManager sensormanager;
    private Sensor temperature;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);


        tvTemp = (TextView) findViewById(R.id.tvTemp);

        sensormanager = (SensorManager)getSystemService(SENSOR_SERVICE);

        temperature= sensormanager.getDefaultSensor(TYPE_AMBIENT_TEMPERATURE);

        if(temperature!=null)
            tvTemp.setText(""+temperature.getPower());
        else
            tvTemp.setText("Sorry - your device doesn't have an ambient temperature sensor");
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

    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_FASTEST);
    }
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() != TYPE_AMBIENT_TEMPERATURE)
        {
            Log.d("mylog","Sorry - Sorry its not  temperature sensor so --> return");
            return;

        }

        if(temperature!=null) {
            tvTemp.setText("" + temperature.getPower());
        }
        else {
            tvTemp.setText("Sorry - your device doesn't have an ambient temperature sensor");
        }
    }


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