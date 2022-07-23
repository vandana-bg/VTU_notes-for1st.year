package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mod3 extends AppCompatActivity {
    Button btn131;
    Button btn132;
    Button btn133;
    Button btn134;
    Button btn135;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod3);
        this.btn131 = (Button) findViewById(C0681R.C0684id.btn131);
        this.btn132 = (Button) findViewById(C0681R.C0684id.btn132);
        this.btn133 = (Button) findViewById(C0681R.C0684id.btn133);
        this.btn134 = (Button) findViewById(C0681R.C0684id.btn134);
        this.btn135 = (Button) findViewById(C0681R.C0684id.btn135);
        this.btn131.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod3.this.startActivity(new Intent(mod3.this.getApplicationContext(), mod131.class));
            }
        });
        this.btn132.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod3.this.startActivity(new Intent(mod3.this.getApplicationContext(), mod132.class));
            }
        });
        this.btn133.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod3.this.startActivity(new Intent(mod3.this.getApplicationContext(), mod133.class));
            }
        });
        this.btn134.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod3.this.startActivity(new Intent(mod3.this.getApplicationContext(), mod134.class));
            }
        });
        this.btn135.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mod3.this.startActivity(new Intent(mod3.this.getApplicationContext(), mod135.class));
            }
        });
    }
}
