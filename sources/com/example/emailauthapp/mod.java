package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod extends AppCompatActivity {
    Button btn111;
    Button btn112;
    Button btn113;
    Button btn114;
    Button btn115;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod);
        this.btn111 = (Button) findViewById(C0681R.C0684id.btn111);
        this.btn112 = (Button) findViewById(C0681R.C0684id.btn112);
        this.btn113 = (Button) findViewById(C0681R.C0684id.btn113);
        this.btn114 = (Button) findViewById(C0681R.C0684id.btn114);
        this.btn115 = (Button) findViewById(C0681R.C0684id.btn115);
        this.btn111.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod.this.startActivity(new Intent(mod.this.getApplicationContext(), mod111.class));
            }
        });
        this.btn112.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod.this.startActivity(new Intent(mod.this.getApplicationContext(), mod112.class));
            }
        });
        this.btn113.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod.this.startActivity(new Intent(mod.this.getApplicationContext(), mod113.class));
            }
        });
        this.btn114.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod.this.startActivity(new Intent(mod.this.getApplicationContext(), mod114.class));
            }
        });
        this.btn115.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod.this.startActivity(new Intent(mod.this.getApplicationContext(), mod115.class));
            }
        });
    }
}
