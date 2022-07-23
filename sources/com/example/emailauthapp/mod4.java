package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod4 extends AppCompatActivity {
    Button btn141;
    Button btn142;
    Button btn143;
    Button btn144;
    Button btn145;
    Button btn146;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod4);
        this.btn141 = (Button) findViewById(C0681R.C0684id.btn141);
        this.btn142 = (Button) findViewById(C0681R.C0684id.btn142);
        this.btn143 = (Button) findViewById(C0681R.C0684id.btn143);
        this.btn144 = (Button) findViewById(C0681R.C0684id.btn144);
        this.btn145 = (Button) findViewById(C0681R.C0684id.btn145);
        this.btn146 = (Button) findViewById(C0681R.C0684id.btn146);
        this.btn141.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod4.this.startActivity(new Intent(mod4.this.getApplicationContext(), mod141.class));
            }
        });
        this.btn142.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod4.this.startActivity(new Intent(mod4.this.getApplicationContext(), mod142.class));
            }
        });
        this.btn143.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod4.this.startActivity(new Intent(mod4.this.getApplicationContext(), mod143.class));
            }
        });
        this.btn144.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod4.this.startActivity(new Intent(mod4.this.getApplicationContext(), mod144.class));
            }
        });
        this.btn145.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod4.this.startActivity(new Intent(mod4.this.getApplicationContext(), mod145.class));
            }
        });
        this.btn146.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod4.this.startActivity(new Intent(mod4.this.getApplicationContext(), mod146.class));
            }
        });
    }
}
