package com.raneem.customlistview;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    ImageView imageView;

    FirebaseDatabase firebaseDatabase;
    String userId="bQr5TzvydkhCKVobaJuAu9uWz463";

    List<Slide> list;
    List<String> listOfIdLectuer;
String sss;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();





        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.customlist, parent, false);
        }
        // get current item to be displayed
       // Slide ss = (Slide) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.titleview);

         final ImageView imageView = (ImageView)
                convertView.findViewById(R.id.imgview12);

        textViewItemName.setText(list.get(position).getTitle());
        imageView.setImageResource(list.get(position).getImg());

/*

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).isBooked()) {
                    imageView.setImageResource(R.drawable.notbookkmark);
                    list.get(position).setBooked(false);
                }
                else {
                    imageView.setImageResource(R.drawable.bookmarkk);
                    list.get(position).setBooked(true);

                }
            }
        });*/

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String lectuer=list.get(position).getTitle();
                if (user != null) {
                    final String userID = user.getUid();
                    mDatabase=  FirebaseDatabase.getInstance().getReference("users").child(userID).child("booked");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Log.d("Before check DB",""+snapshot.toString());

                            if(snapshot.exists()) {

                                Log.d("check DB", "" + snapshot.toString());
                                if (isemptyimag) {
                                    imageView.setImageResource(R.drawable.bookmarkk);
                                    isemptyimag = false;
                                   firebaseDatabase.getReference().child("users").child(userID).child("booked").setValue(lectuer);



                                } else {

                                    imageView.setImageResource(R.drawable.notbookkmark);
                                   isemptyimag = true;
                                    firebaseDatabase.getReference().child("users").child(userID).child("booked").child(lectuer).removeValue();


                                }
                                //if (list.get(position).isBooked()) {

                                //imageView.setImageResource(R.drawable.notbookkmark);
                               // firebaseDatabase.getReference().child("users").child(userID).child("booked").child(lectuer).removeValue();

                               // list.get(position).setBooked(false);
                         //   }


                          //  }  else {

                               //imageView.setImageResource(R.drawable.bookmarkk);
                   //firebaseDatabase.getReference().child("users").child("bQr5TzvydkhCKVobaJuAu9uWz463").child("booked").setValue(listOfIdLectuer);
                              //  list.get(position).setBooked(true);

                            }
                                }





                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                //return convertView;


            }

    }
});



        return convertView;

}
}

