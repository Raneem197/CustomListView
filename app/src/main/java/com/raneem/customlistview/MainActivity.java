package com.raneem.customlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    StorageReference storageRef;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    List<Slide>list=new ArrayList<>();
    List<String> listOfIdLectuer=new  ArrayList<>();
    String userId="bQr5TzvydkhCKVobaJuAu9uWz463";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        storageRef= FirebaseStorage.getInstance().getReference();

        ViewAllFailes();
        //chekbook();
        //ReadFairebaseFailes();
        }

  /*  private void chekbook() {
        MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
       adapter.getItem()
               adapter.getView( int position, View convertView,  parent) {}
        list.get(position).getImg();
        listview.getAdapter();


            final String lectuer=listview.getAdapter().getItem();
            if (user != null) {
                final String userID = user.getUid();
                mDatabase=  FirebaseDatabase.getInstance().getReference("users").child(userID).child("booked").child(lectuer);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Log.d("Before check DB",""+snapshot.toString());

                        if(snapshot.exists()) {

                            Log.d("check DB",""+snapshot.toString());
                            imageView.setImageResource(R.drawable.bookmark);
                            // firebaseDatabase.getReference().child("users").child(userID).child("booked").child(lectuer).removeValue();





                        }  else {

                            imageView.setImageResource(R.drawable.bookmarkk);
                            //   firebaseDatabase.getReference().child("users").child("bQr5TzvydkhCKVobaJuAu9uWz463").child("booked").setValue(lectuer);


                        }
                    }





                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                //return convertView;


            }

        }
*/

    public void ViewAllFailes () {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("JavaOneSlides");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Slide ss = ds.getValue(Slide.class);
                    ss.setId(ds.getKey());
                    list.add(ss);



                }


                MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
                listview.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }
    private void ReadFairebaseFailes() {
        Slide s1=new Slide("JavaBasics","https://firebasestorage.googleapis.com/v0/b/customlistview-ea001.appspot.com/o/failes%2FJavaBasics%20(I).pdf?alt=media&token=fe429b06-9189-4a6e-aa63-3f3f0db832af"
                ,R.drawable.notbookkmark);
        Slide s2=new Slide("methods "," https://firebasestorage.googleapis.com/v0/b/customlistview-ea001.appspot.com/o/failes%2FLec%237%268%20-%20Methods-converted.pdf?alt=media&token=53dd1e29-5ad4-4532-bf36-5725304ff404",
                R.drawable.notbookkmark);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s1);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s2);

    }
}