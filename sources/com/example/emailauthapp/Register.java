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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText confPassword;
    FirebaseAuth fAuth;
    Button gotoLogin;
    Button registerBtn;
    EditText registerEmail;
    EditText registerFullName;
    EditText registerPassword;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0681R.layout.activity_register);
        this.registerFullName = (EditText) findViewById(C0681R.C0684id.registerFullName);
        this.registerEmail = (EditText) findViewById(C0681R.C0684id.registerEmail);
        this.registerPassword = (EditText) findViewById(C0681R.C0684id.registerPassword);
        this.confPassword = (EditText) findViewById(C0681R.C0684id.confPassword);
        this.gotoLogin = (Button) findViewById(C0681R.C0684id.gotoLogin);
        this.registerBtn = (Button) findViewById(C0681R.C0684id.registerBtn);
        this.fAuth = FirebaseAuth.getInstance();
        this.gotoLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Register.this.startActivity(new Intent(Register.this.getApplicationContext(), Login.class));
                Register.this.finish();
            }
        });
        this.registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String fullname = Register.this.registerFullName.getText().toString();
                String Email = Register.this.registerEmail.getText().toString();
                String Password = Register.this.registerPassword.getText().toString();
                String confPass = Register.this.confPassword.getText().toString();
                if (fullname.isEmpty()) {
                    Register.this.registerFullName.setError("Full Name is Required");
                } else if (Email.isEmpty()) {
                    Register.this.registerEmail.setError("Email is Required");
                } else if (Password.isEmpty()) {
                    Register.this.registerPassword.setError("Password is Required");
                } else if (confPass.isEmpty()) {
                    Register.this.confPassword.setError("Re enter the password");
                } else if (!Password.equals(confPass)) {
                    Register.this.confPassword.setError("Password doesnt match");
                } else {
                    Toast.makeText(Register.this, "Data Validated", 0).show();
                    Register.this.fAuth.createUserWithEmailAndPassword(Email, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        public void onSuccess(AuthResult authResult) {
                            Register.this.startActivity(new Intent(Register.this.getApplicationContext(), Login.class));
                            Register.this.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        public void onFailure(Exception e) {
                            Toast.makeText(Register.this, e.getMessage(), 0).show();
                        }
                    });
                }
            }
        });
    }
}
