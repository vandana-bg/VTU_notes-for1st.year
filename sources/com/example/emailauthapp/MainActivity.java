package com.example.emailauthapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button asD;
    FirebaseAuth auth;
    LayoutInflater inflater;
    Button logout;
    AlertDialog.Builder reset_alert;
    TextView verifyEmail;
    Button verifybtn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0681R.layout.activity_main);
        this.logout = (Button) findViewById(C0681R.C0684id.logout);
        this.verifyEmail = (TextView) findViewById(C0681R.C0684id.verifyEmail);
        this.verifybtn = (Button) findViewById(C0681R.C0684id.verifybtn);
        this.auth = FirebaseAuth.getInstance();
        this.reset_alert = new AlertDialog.Builder(this);
        this.inflater = getLayoutInflater();
        Button button = (Button) findViewById(C0681R.C0684id.asD);
        this.asD = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!MainActivity.this.auth.getCurrentUser().isEmailVerified()) {
                    Toast.makeText(MainActivity.this, "Please check your Email", 0).show();
                } else {
                    MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), Sem.class));
                }
            }
        });
        if (!this.auth.getCurrentUser().isEmailVerified()) {
            this.verifybtn.setVisibility(0);
            this.verifyEmail.setVisibility(0);
        }
        this.verifybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Verification Email sent", 0).show();
                        MainActivity.this.verifybtn.setVisibility(8);
                        MainActivity.this.verifyEmail.setVisibility(8);
                    }
                });
                FirebaseAuth.getInstance().signOut();
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), Login.class));
                MainActivity.this.finish();
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), Login.class));
                MainActivity.this.finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0681R.C0686menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0681R.C0684id.resetUserPassword) {
            startActivity(new Intent(getApplicationContext(), Reset.class));
        }
        if (item.getItemId() == C0681R.C0684id.updateEmail) {
            final View v = this.inflater.inflate(C0681R.layout.reset_pop, (ViewGroup) null);
            this.reset_alert.setTitle((CharSequence) "Update Email").setMessage((CharSequence) "Enter new email").setPositiveButton((CharSequence) "update", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText email = (EditText) v.findViewById(C0681R.C0684id.reset_pass_pop);
                    if (email.getText().toString().isEmpty()) {
                        email.setError("Required Field");
                    } else {
                        MainActivity.this.auth.getCurrentUser().updateEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this, "Email updated", 0).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            public void onFailure(Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), 0).show();
                            }
                        });
                    }
                }
            }).setNegativeButton((CharSequence) "cancel", (DialogInterface.OnClickListener) null).setView(v).create().show();
        }
        if (item.getItemId() == C0681R.C0684id.delete) {
            View inflate = this.inflater.inflate(C0681R.layout.reset_pop, (ViewGroup) null);
            this.reset_alert.setTitle((CharSequence) "Delete Account Permanently ?").setMessage((CharSequence) "Are u sure?").setPositiveButton((CharSequence) "delete", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.auth.getCurrentUser().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity.this, "account deleted", 0).show();
                            MainActivity.this.auth.signOut();
                            MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), Login.class));
                            MainActivity.this.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        public void onFailure(Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), 0).show();
                        }
                    });
                }
            }).setNegativeButton((CharSequence) "cancel", (DialogInterface.OnClickListener) null).create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}
