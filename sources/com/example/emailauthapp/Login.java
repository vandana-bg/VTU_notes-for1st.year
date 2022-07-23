package com.example.emailauthapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button createAccountBtn;
    FirebaseAuth firebaseAuth;
    LayoutInflater inflater;
    EditText loginName;
    EditText loginPassword;
    Button loginbtn;
    AlertDialog.Builder reset_alert;
    Button resett;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0681R.layout.activity_login);
        this.createAccountBtn = (Button) findViewById(C0681R.C0684id.createAccountBtn);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.reset_alert = new AlertDialog.Builder(this);
        this.inflater = getLayoutInflater();
        this.createAccountBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login.this.startActivity(new Intent(Login.this.getApplicationContext(), Register.class));
            }
        });
        this.loginbtn = (Button) findViewById(C0681R.C0684id.loginbtn);
        this.loginName = (EditText) findViewById(C0681R.C0684id.loginName);
        this.loginPassword = (EditText) findViewById(C0681R.C0684id.loginPassword);
        Button button = (Button) findViewById(C0681R.C0684id.resett);
        this.resett = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final View v = Login.this.inflater.inflate(C0681R.layout.reset_pop, (ViewGroup) null);
                Login.this.reset_alert.setTitle((CharSequence) "reset forget password ?").setMessage((CharSequence) "Enter your email to get password reset link").setPositiveButton((CharSequence) "reset", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText email = (EditText) v.findViewById(C0681R.C0684id.reset_pass_pop);
                        if (email.getText().toString().isEmpty()) {
                            email.setError("Required Field");
                        } else {
                            Login.this.firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Login.this, "Reset email sent", 0).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                public void onFailure(Exception e) {
                                    Toast.makeText(Login.this, e.getMessage(), 0).show();
                                }
                            });
                        }
                    }
                }).setNegativeButton((CharSequence) "cancel", (DialogInterface.OnClickListener) null).setView(v).create().show();
            }
        });
        this.loginbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Login.this.loginName.getText().toString().isEmpty()) {
                    Login.this.loginName.setError("Enter email");
                } else if (Login.this.loginPassword.getText().toString().isEmpty()) {
                    Login.this.loginPassword.setError("enter the password");
                } else {
                    Login.this.firebaseAuth.signInWithEmailAndPassword(Login.this.loginName.getText().toString(), Login.this.loginPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        public void onSuccess(AuthResult authResult) {
                            if (!Login.this.firebaseAuth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(Login.this, "Please verify your Email", 0).show();
                                return;
                            }
                            Toast.makeText(Login.this, "Login is succesfull", 0).show();
                            Login.this.startActivity(new Intent(Login.this.getApplicationContext(), MainActivity.class));
                            Login.this.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        public void onFailure(Exception e) {
                            Toast.makeText(Login.this, e.getMessage(), 0).show();
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}
