package com.example.efraimsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TransferQueue;

class CustomAdapter extends ArrayAdapter<Products> {
    private Context context;
    private ArrayList <Products> list;

    public CustomAdapter(Context context, ArrayList <Products> list){
        super(context, R.layout.my_row, list);
        this.list=list;
        this.context = context;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_row, parent, false);
        Products product = list.get(position);
        TextView id = (TextView) view.findViewById(R.id.product_id_txt);
        TextView name = (TextView) view.findViewById(R.id.product_name_txt);
        TextView price = (TextView) view.findViewById(R.id.product_price_txt);
        TextView description = (TextView) view.findViewById(R.id.description_txt);
        ImageView ivProduct = (ImageView) view.findViewById(R.id.imageView2);
        ivProduct.setImageBitmap(product.getPic());
        id.setText(String.valueOf(product.getId()));
        name.setText(String.valueOf(product.getName()));
        price.setText(String.valueOf(product.getPrice()));
        description.setText(String.valueOf(product.getDescription()));
        return view;
    }
}
