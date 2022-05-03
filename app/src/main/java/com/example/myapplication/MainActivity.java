package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    FirebaseUser fUser;
    private DatabaseReference mDataBase;
    private ListView listView;
    private ArrayAdapter<Child> adapter;
    private List<Child> listData;
    private String[] name;
    private String[] lastName;
    private String[] fatherName;
    private String[] birthDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fUser = FirebaseAuth.getInstance().getCurrentUser();

        for (int i = 0; i < listData.size(); i++) {
            Child child = listData.get(i);
            name[i] = child.firstName;
            lastName[i] = child.lastName;
            fatherName[i] = child.fatherName;
            birthDay[i] = child.birthDay;
        }
        ChildArrayAdapter childArrayAdapter = new ChildArrayAdapter(this, name, lastName, fatherName, birthDay);
        listView.setAdapter(childArrayAdapter);

    }
    public void init(){

        mDataBase = FirebaseDatabase.getInstance().getReference("Child");
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);

        getDataDB();
        name = new String[listData.size()];
        lastName = new String[listData.size()];
        fatherName = new String[listData.size()];
        birthDay = new String[listData.size()];

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(fUser==null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }

    }
    public void click(View view){
        startActivity(new Intent(MainActivity.this,AddChildActivity.class));
    }
    public void getDataDB(){
    ValueEventListener vListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
        for (DataSnapshot ds : snapshot.getChildren()){
            Child child = ds.getValue(Child.class);
            listData.add(child);

        }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    mDataBase.addValueEventListener(vListener);
    }



}