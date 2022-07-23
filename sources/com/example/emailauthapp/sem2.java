package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class sem2 extends AppCompatActivity {
    Button sem21;
    Button sem22;
    Button sem23;
    Button sem24;
    Button sem25;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_sem2);
        this.sem21 = (Button) findViewById(C0681R.C0684id.sem21);
        this.sem22 = (Button) findViewById(C0681R.C0684id.sem22);
        this.sem23 = (Button) findViewById(C0681R.C0684id.sem23);
        this.sem24 = (Button) findViewById(C0681R.C0684id.sem24);
        this.sem25 = (Button) findViewById(C0681R.C0684id.sem25);
        this.sem21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem2.this.startActivity(new Intent(sem2.this.getApplicationContext(), mod21.class));
            }
        });
        this.sem22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem2.this.startActivity(new Intent(sem2.this.getApplicationContext(), mod22.class));
            }
        });
        this.sem23.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem2.this.startActivity(new Intent(sem2.this.getApplicationContext(), mod23.class));
            }
        });
        this.sem24.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem2.this.startActivity(new Intent(sem2.this.getApplicationContext(), mod24.class));
            }
        });
        this.sem25.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sem2.this.startActivity(new Intent(sem2.this.getApplicationContext(), mod25.class));
            }
        });
    }
}
