package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class sem1 extends AppCompatActivity {
    Button sem11;
    Button sem12;
    Button sem13;
    Button sem14;
    Button sem15;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_sem1);
        this.sem11 = (Button) findViewById(C0681R.C0684id.sem11);
        this.sem12 = (Button) findViewById(C0681R.C0684id.sem12);
        this.sem13 = (Button) findViewById(C0681R.C0684id.sem13);
        this.sem14 = (Button) findViewById(C0681R.C0684id.sem14);
        this.sem15 = (Button) findViewById(C0681R.C0684id.sem15);
        this.sem11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem1.this.startActivity(new Intent(sem1.this.getApplicationContext(), mod.class));
            }
        });
        this.sem12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem1.this.startActivity(new Intent(sem1.this.getApplicationContext(), mod2.class));
            }
        });
        this.sem13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem1.this.startActivity(new Intent(sem1.this.getApplicationContext(), mod3.class));
            }
        });
        this.sem14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem1.this.startActivity(new Intent(sem1.this.getApplicationContext(), mod4.class));
            }
        });
        this.sem15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem1.this.startActivity(new Intent(sem1.this.getApplicationContext(), mod5.class));
            }
        });
    }
}
