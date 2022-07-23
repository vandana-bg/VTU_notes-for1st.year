package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod25 extends AppCompatActivity {
    Button btn251;
    Button btn252;
    Button btn253;
    Button btn254;
    Button btn255;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod25);
        this.btn251 = (Button) findViewById(C0681R.C0684id.btn251);
        this.btn252 = (Button) findViewById(C0681R.C0684id.btn252);
        this.btn253 = (Button) findViewById(C0681R.C0684id.btn253);
        this.btn254 = (Button) findViewById(C0681R.C0684id.btn254);
        this.btn255 = (Button) findViewById(C0681R.C0684id.btn255);
        this.btn251.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod25.this.startActivity(new Intent(mod25.this.getApplicationContext(), mod251.class));
            }
        });
        this.btn252.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod25.this.startActivity(new Intent(mod25.this.getApplicationContext(), mod252.class));
            }
        });
        this.btn253.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod25.this.startActivity(new Intent(mod25.this.getApplicationContext(), mod253.class));
            }
        });
        this.btn254.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod25.this.startActivity(new Intent(mod25.this.getApplicationContext(), mod254.class));
            }
        });
        this.btn255.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod25.this.startActivity(new Intent(mod25.this.getApplicationContext(), mod255.class));
            }
        });
    }
}
