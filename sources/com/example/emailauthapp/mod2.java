package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod2 extends AppCompatActivity {
    Button btn121;
    Button btn122;
    Button btn123;
    Button btn124;
    Button btn125;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod2);
        this.btn121 = (Button) findViewById(C0681R.C0684id.btn121);
        this.btn122 = (Button) findViewById(C0681R.C0684id.btn122);
        this.btn123 = (Button) findViewById(C0681R.C0684id.btn123);
        this.btn124 = (Button) findViewById(C0681R.C0684id.btn124);
        this.btn125 = (Button) findViewById(C0681R.C0684id.btn125);
        this.btn121.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod2.this.startActivity(new Intent(mod2.this.getApplicationContext(), mod121.class));
            }
        });
        this.btn122.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod2.this.startActivity(new Intent(mod2.this.getApplicationContext(), mod122.class));
            }
        });
        this.btn123.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod2.this.startActivity(new Intent(mod2.this.getApplicationContext(), mod123.class));
            }
        });
        this.btn124.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod2.this.startActivity(new Intent(mod2.this.getApplicationContext(), mod124.class));
            }
        });
        this.btn125.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod2.this.startActivity(new Intent(mod2.this.getApplicationContext(), mod125.class));
            }
        });
    }
}
