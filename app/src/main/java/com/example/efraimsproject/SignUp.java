package com.example.efraimsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private Button signUp;
    private Context context;
    private EditText userName, password;
    SharedPreferences sp;
    CheckBox cb;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = this;
        signUp = findViewById(R.id.signUpButton);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cb=findViewById(R.id.checkbox);
        sp=getSharedPreferences("details1",MODE_PRIVATE);

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

    public void signUpClick(View view) {
        DataBase myUser = new DataBase(SignUp.this);
        if ((userName.getText().length() > 0) && (password.getText().toString().length()>0))
        {

            if(myUser.doesExist(userName.getText().toString())==0)
            { if (cb.isChecked())
            {
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("username",userName.getText().toString());
                editor.putString("password",password.getText().toString());
                editor.commit();
            }
                myUser.insertUser(userName.getText().toString(), password.getText().toString());

                Intent intent = new Intent(this, Main2.class);
                intent.putExtra("id", (myUser.getId(userName.getText().toString(), password.getText().toString())).toString());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(context, "Username exists!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context, "No data detected!", Toast.LENGTH_SHORT).show();
        }
    }
}