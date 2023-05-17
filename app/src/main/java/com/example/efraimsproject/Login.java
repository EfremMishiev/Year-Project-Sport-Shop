package com.example.efraimsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SharedPreferences sp;
    private EditText userName, password;
    Context context;
    int countUsers;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        sp=getSharedPreferences("details1",MODE_PRIVATE);
        String strusername = sp.getString("username",null);
        String strpassword=sp.getString("password",null);
        if(strpassword!=null&&strusername!=null)
         {
            userName.setText(strusername);
           password.setText(strpassword);
         }

    }

    public void loginClick(View view) {
        DataBase myUser = new DataBase(Login.this);
        countUsers = myUser.checkForUser(userName.getText().toString(),password.getText().toString());

        if (countUsers==1)
        {
            Intent intent = new Intent(this, Main2.class);
            intent.putExtra("id", (myUser.getId(userName.getText().toString(), password.getText().toString())).toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(context, "No such user!", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginClick2(View view) {
        DataBase myUser = new DataBase(Login.this);
        countUsers = myUser.checkForUser(userName.getText().toString(),password.getText().toString());

        if (countUsers==1)
        {
            Intent intent = new Intent(this, Main2.class);
            intent.putExtra("id", (myUser.getId(userName.getText().toString(), password.getText().toString())).toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(context, "No such user!", Toast.LENGTH_SHORT).show();
        }
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