package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddChildActivity extends AppCompatActivity {
    EditText firstName,lastName,fatherName;
    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        init();
    }
    public void init(){
        firstName = findViewById(R.id.editTextTextPersonName3);
        lastName = findViewById(R.id.editTextTextPersonName4);
        fatherName = findViewById(R.id.editTextTextPersonName5);
        firebaseDatabase  = FirebaseDatabase.getInstance().getReference("Child");

    }
    public void setBD(View view){

        Child child = new Child(firstName.getText().toString(),lastName.getText().toString(),fatherName.getText().toString());
        firebaseDatabase.push().setValue(child);
        startActivity(new Intent(AddChildActivity.this, MainActivity.class));
    }
}