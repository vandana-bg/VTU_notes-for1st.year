package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod23 extends AppCompatActivity {
    Button btn231;
    Button btn232;
    Button btn233;
    Button btn234;
    Button btn235;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod23);
        this.btn231 = (Button) findViewById(C0681R.C0684id.btn231);
        this.btn232 = (Button) findViewById(C0681R.C0684id.btn232);
        this.btn233 = (Button) findViewById(C0681R.C0684id.btn233);
        this.btn234 = (Button) findViewById(C0681R.C0684id.btn234);
        this.btn235 = (Button) findViewById(C0681R.C0684id.btn235);
        this.btn231.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod23.this.startActivity(new Intent(mod23.this.getApplicationContext(), mod231.class));
            }
        });
        this.btn232.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod23.this.startActivity(new Intent(mod23.this.getApplicationContext(), mod232.class));
            }
        });
        this.btn233.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod23.this.startActivity(new Intent(mod23.this.getApplicationContext(), mod233.class));
            }
        });
        this.btn234.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod23.this.startActivity(new Intent(mod23.this.getApplicationContext(), mod234.class));
            }
        });
        this.btn235.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod23.this.startActivity(new Intent(mod23.this.getApplicationContext(), mod235.class));
            }
        });
    }
}
