package com.example.efraimsproject;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProduct extends AppCompatActivity {
    EditText product_name, price, description;
    Button  save_button;
    ImageButton imageButton;
    Bitmap bitmap;
    DataBase2 coh;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        product_name = findViewById(R.id.product_name);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        save_button = findViewById(R.id.save_button);
        coh = new DataBase2(this);
        imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        });

        save_button.setOnClickListener(view -> {
            DataBase2 myDB = new DataBase2(AddProduct.this);
            String name = product_name.getText().toString();
            int price1 = Integer.parseInt(price.getText().toString());
            String description1 = description.getText().toString();
            if (bitmap == null){
                bitmap = (BitmapFactory.decodeResource(getResources(), R.drawable.khabib));
            }
            else{
                imageButton.setImageBitmap(bitmap);
            }
            Products product = new Products(name, bitmap , description1, price1);
            long id = coh.addProduct(product);
            Toast.makeText(AddProduct.this, "Product added successfully", Toast.LENGTH_SHORT).show();



        });
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

    protected void onActivityResult( int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK)
        {
            if(resultCode==RESULT_OK)
            {
                bitmap = (Bitmap)data.getExtras().get("data");
                if(bitmap!=null) imageButton.setImageBitmap(bitmap);
                else imageButton.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.khabib));

            }
        }
    }
}