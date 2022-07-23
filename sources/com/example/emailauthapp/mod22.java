package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod22 extends AppCompatActivity {
    Button btn221;
    Button btn222;
    Button btn223;
    Button btn224;
    Button btn225;
    Button btn226;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod22);
        this.btn221 = (Button) findViewById(C0681R.C0684id.btn221);
        this.btn222 = (Button) findViewById(C0681R.C0684id.btn222);
        this.btn223 = (Button) findViewById(C0681R.C0684id.btn223);
        this.btn224 = (Button) findViewById(C0681R.C0684id.btn224);
        this.btn225 = (Button) findViewById(C0681R.C0684id.btn225);
        this.btn226 = (Button) findViewById(C0681R.C0684id.btn226);
        this.btn221.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod22.this.startActivity(new Intent(mod22.this.getApplicationContext(), mod221.class));
            }
        });
        this.btn222.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod22.this.startActivity(new Intent(mod22.this.getApplicationContext(), mod222.class));
            }
        });
        this.btn223.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod22.this.startActivity(new Intent(mod22.this.getApplicationContext(), mod223.class));
            }
        });
        this.btn224.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod22.this.startActivity(new Intent(mod22.this.getApplicationContext(), mod224.class));
            }
        });
        this.btn225.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod22.this.startActivity(new Intent(mod22.this.getApplicationContext(), mod225.class));
            }
        });
        this.btn226.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod22.this.startActivity(new Intent(mod22.this.getApplicationContext(), che226.class));
            }
        });
    }
}
