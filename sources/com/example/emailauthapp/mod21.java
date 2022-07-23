package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod21 extends AppCompatActivity {
    Button btn211;
    Button btn212;
    Button btn213;
    Button btn214;
    Button btn215;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod21);
        this.btn211 = (Button) findViewById(C0681R.C0684id.btn211);
        this.btn212 = (Button) findViewById(C0681R.C0684id.btn212);
        this.btn213 = (Button) findViewById(C0681R.C0684id.btn213);
        this.btn214 = (Button) findViewById(C0681R.C0684id.btn214);
        this.btn215 = (Button) findViewById(C0681R.C0684id.btn215);
        this.btn211.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod21.this.startActivity(new Intent(mod21.this.getApplicationContext(), mod211.class));
            }
        });
        this.btn212.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod21.this.startActivity(new Intent(mod21.this.getApplicationContext(), mod212.class));
            }
        });
        this.btn213.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod21.this.startActivity(new Intent(mod21.this.getApplicationContext(), mod213.class));
            }
        });
        this.btn214.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod21.this.startActivity(new Intent(mod21.this.getApplicationContext(), mod214.class));
            }
        });
        this.btn215.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod21.this.startActivity(new Intent(mod21.this.getApplicationContext(), mod215.class));
            }
        });
    }
}
