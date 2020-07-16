package com.example.application.pizzapizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    private FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fireAuth = FirebaseAuth.getInstance();

    }

    public void logout(View view){
        fireAuth.signOut();
        Intent i = new Intent(this,signupActivity.class);
        startActivity(i);
        finish();

    }

    public void mealMenuClick(View view) {
        Intent intent = new Intent(this,mealMenu.class);
        startActivity(intent);
    }
    public void pizzaMenuClick(View view){
        Intent intent = new Intent(this,pizzaMenu.class);
        startActivity(intent);
    }

    public void drinkMenuClick(View view) {
        Intent intent = new Intent(this,drinkMenu.class);
        startActivity(intent);
    }


    public void myAccClick(View view) {
        Intent intent = new Intent(this,myAcc.class);
        startActivity(intent);
    }
}

