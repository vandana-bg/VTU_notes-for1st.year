package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Reset extends AppCompatActivity {
    EditText resetPass;
    EditText resetPassw;
    Button resetbtn;
    FirebaseUser user;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_reset);
        this.resetbtn = (Button) findViewById(C0681R.C0684id.resetbtn);
        this.resetPass = (EditText) findViewById(C0681R.C0684id.resetPass);
        this.resetPassw = (EditText) findViewById(C0681R.C0684id.resetPassw);
        this.user = FirebaseAuth.getInstance().getCurrentUser();
        this.resetbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Reset.this.resetPass.getText().toString().isEmpty()) {
                    Reset.this.resetPass.setError("Required Field");
                } else if (Reset.this.resetPassw.getText().toString().isEmpty()) {
                    Reset.this.resetPassw.setError("Required Field");
                } else if (!Reset.this.resetPass.getText().toString().equals(Reset.this.resetPassw.getText().toString())) {
                    Reset.this.resetPassw.setError("Password Dont not match");
                } else {
                    Reset.this.user.updatePassword(Reset.this.resetPass.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        public void onSuccess(Void unused) {
                            Toast.makeText(Reset.this, "password updated", 0).show();
                            Reset.this.startActivity(new Intent(Reset.this.getApplicationContext(), MainActivity.class));
                            Reset.this.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        public void onFailure(Exception e) {
                            Toast.makeText(Reset.this, e.getMessage(), 0).show();
                        }
                    });
                }
            }
        });
    }
}
