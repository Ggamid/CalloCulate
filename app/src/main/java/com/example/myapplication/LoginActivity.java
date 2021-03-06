package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText edEmail,edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    public void init(){
        firebaseAuth = FirebaseAuth.getInstance();
        edEmail = findViewById(R.id.UserEmail);
        edPassword = findViewById(R.id.UserPassword);
    }
    public void reg(View view){
        startActivity(new Intent(LoginActivity.this,RegActivity.class));
    }
    public void clickSingIn(View view){
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                });
    }
}