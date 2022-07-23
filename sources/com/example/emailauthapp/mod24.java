package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod24 extends AppCompatActivity {
    Button btn241;
    Button btn242;
    Button btn243;
    Button btn244;
    Button btn245;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod24);
        this.btn241 = (Button) findViewById(C0681R.C0684id.btn241);
        this.btn242 = (Button) findViewById(C0681R.C0684id.btn242);
        this.btn243 = (Button) findViewById(C0681R.C0684id.btn243);
        this.btn244 = (Button) findViewById(C0681R.C0684id.btn244);
        this.btn245 = (Button) findViewById(C0681R.C0684id.btn245);
        this.btn241.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod24.this.startActivity(new Intent(mod24.this.getApplicationContext(), mod241.class));
            }
        });
        this.btn242.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod24.this.startActivity(new Intent(mod24.this.getApplicationContext(), mod242.class));
            }
        });
        this.btn243.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod24.this.startActivity(new Intent(mod24.this.getApplicationContext(), mod243.class));
            }
        });
        this.btn244.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod24.this.startActivity(new Intent(mod24.this.getApplicationContext(), mod244.class));
            }
        });
        this.btn245.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod24.this.startActivity(new Intent(mod24.this.getApplicationContext(), mod245.class));
            }
        });
    }
}
