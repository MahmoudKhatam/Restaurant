package com.example.application.pizzapizza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {
    EditText email , pass ;
    Button sIn ,sUp ;
    TextView creatAcc ;
    ProgressBar progressBar;
   private FirebaseAuth fAuth ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email = (EditText) findViewById(R.id.writtenEmailSin);
        pass = (EditText)findViewById(R.id.writtenpass);
        sIn = (Button) findViewById(R.id.signin);
        sUp = (Button) findViewById(R.id.signup);
        creatAcc = (TextView)findViewById(R.id.createAcc);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        sIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myEmail = email.getText().toString().trim();
                String myPass = pass.getText().toString().trim();
                if (TextUtils.isEmpty(myEmail)){
                    email.setError("please enter ur email");
                    return;
                }
                if (TextUtils.isEmpty(myPass)){
                    pass.setError("please enter ur pass");
                    return;
                }
                if (pass.length() <= 5){
                    pass.setError("pass must be at least 6 char");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(myEmail,myPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(signin.this, "welcome to home", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(signin.this,home.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(signin.this, "Error.."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

            }
        });
        sUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signin.this,signupActivity.class);
                startActivity(i);
            }
        });


    }


    public void movetosignup(View view) {
        Intent i = new Intent(this,signupActivity.class);
        startActivity(i);
    }
}
