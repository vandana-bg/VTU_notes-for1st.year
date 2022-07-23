package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod5 extends AppCompatActivity {
    Button btn151;
    Button btn152;
    Button btn153;
    Button btn154;
    Button btn155;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod5);
        this.btn151 = (Button) findViewById(C0681R.C0684id.btn151);
        this.btn152 = (Button) findViewById(C0681R.C0684id.btn152);
        this.btn153 = (Button) findViewById(C0681R.C0684id.btn153);
        this.btn154 = (Button) findViewById(C0681R.C0684id.btn154);
        this.btn155 = (Button) findViewById(C0681R.C0684id.btn155);
        this.btn151.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod5.this.startActivity(new Intent(mod5.this.getApplicationContext(), mod151.class));
            }
        });
        this.btn152.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod5.this.startActivity(new Intent(mod5.this.getApplicationContext(), mod152.class));
            }
        });
        this.btn153.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod5.this.startActivity(new Intent(mod5.this.getApplicationContext(), mod153.class));
            }
        });
        this.btn154.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod5.this.startActivity(new Intent(mod5.this.getApplicationContext(), mod154.class));
            }
        });
        this.btn155.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod5.this.startActivity(new Intent(mod5.this.getApplicationContext(), mod155.class));
            }
        });
    }
}
