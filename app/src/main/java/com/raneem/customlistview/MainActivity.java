package com.raneem.customlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageRef= FirebaseStorage.getInstance().getReference();

        ViewAllFailes();
        ReadFairebaseFailes(); }
    public void ViewAllFailes () {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("slides");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Slide ss = ds.getValue(Slide.class);
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
        Slide s1=new Slide("JavaBasics","https://firebasestorage.googleapis.com/v0/b/customlistview-ea001.appspot.com/o/failes%2FLec%237%268%20-%20Methods-converted.pdf?alt=media&token=53dd1e29-5ad4-4532-bf36-5725304ff404"
                ,R.drawable.bookmark);
        firebaseDatabase.getReference("slides").push().setValue(s1);
    }
}