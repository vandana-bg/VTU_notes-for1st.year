package com.example.emailauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Sem extends AppCompatActivity {
    Button sem1Btn;
    Button sem2Btn;

    /* renamed from: tB */
    TextView f111tB;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_sem);
        this.sem1Btn = (Button) findViewById(C0681R.C0684id.sem1Btn);
        this.sem2Btn = (Button) findViewById(C0681R.C0684id.sem2Btn);
        this.f111tB = (TextView) findViewById(C0681R.C0684id.f89tB);
        this.sem1Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Sem.this.startActivity(new Intent(Sem.this.getApplicationContext(), sem1.class));
            }
        });
        this.sem2Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Sem.this.startActivity(new Intent(Sem.this.getApplicationContext(), sem2.class));
            }
        });
    }
}
