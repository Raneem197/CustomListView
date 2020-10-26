package com.raneem.customlistview;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter   extends BaseAdapter  {

    private Context context; //context
    DatabaseReference mDatabase;

    FirebaseDatabase firebaseDatabase;

    List<Slide> list;

boolean isemptyimag=true;
    public MyAdapter(Context context,  List<Slide> list) {
        this.context = context;
        this.list = list;


    }


    @Override
    public int getCount() {
        return list.size(); //returns total of items in the list;
    }

    @Override
    public Object getItem(int position) {

        return list.get(position); //returns list item at the specified position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row





            if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.customlist, parent, false);
        }
        // get current item to be displayed
        Slide ss = (Slide) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.titleview);

         final ImageView imageView = (ImageView)
                convertView.findViewById(R.id.imgview12);

        textViewItemName.setText(list.get(position).getTitle());
        imageView.setImageResource(list.get(position).getImg());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)



            {

                if(isemptyimag) {
                    imageView.setImageResource(R.drawable.bookmarkk);
                    isemptyimag = false;

                }else {

                    imageView.setImageResource(R.drawable.bookmark);
                    isemptyimag=true;


                }

            }
        });
        return convertView;


    }




}
