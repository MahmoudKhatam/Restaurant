package com.example.application.pizzapizza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signupActivity extends AppCompatActivity {
    EditText unSup , pasSIn , email , phone;
    Button done ;
    private FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fstore ;
    String uID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        unSup = (EditText)findViewById(R.id.writtenusernameSignup);
        pasSIn = (EditText) findViewById(R.id.writtenpassSignup);
        email = (EditText) findViewById(R.id.writtenemailSignup);
        phone = (EditText) findViewById(R.id.writtenphoneSignup);
        done = (Button) findViewById(R.id.doneSignup);
        progressBar = findViewById(R.id.progressBar2);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser()!=null){
            Intent i = new Intent(signupActivity.this,home.class);
            startActivity(i);
            finish();
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameSignUp = unSup.getText().toString().trim();
                String passwordSignUp = pasSIn.getText().toString().trim();
                final String emailSignUp = email.getText().toString().trim();
                final String phoneSignUp = phone.getText().toString().trim();

                if (TextUtils.isEmpty(usernameSignUp)){
                    unSup.setError("username is required");
                    return;
                }
                if (TextUtils.isEmpty(passwordSignUp)){
                    pasSIn.setError("pass is required");
                    return;
                }
                if (TextUtils.isEmpty(emailSignUp)){
                    email.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(phoneSignUp)){
                    phone.setError("phone is required");
                    return;
                }
                if (passwordSignUp.length()<=5){
                    pasSIn.setError("pass must be at least 6 char ");
                    return;
                }
                progressBar.setVisibility(View.INVISIBLE);

                fAuth.createUserWithEmailAndPassword(emailSignUp,passwordSignUp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signupActivity.this, "successful registration", Toast.LENGTH_SHORT).show();
                            uID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("Users").document(uID);
                            final Map<String,Object> user = new HashMap<>();
                            user.put("Uname",usernameSignUp);
                            user.put("Email",emailSignUp);
                            user.put("Phone",phoneSignUp);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("tag","user info saved to firebase"+uID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("message","fail"+e.toString());
                                }
                            });

                            Intent i = new Intent(signupActivity.this,home.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(signupActivity.this, "Error.."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });


    }
}
