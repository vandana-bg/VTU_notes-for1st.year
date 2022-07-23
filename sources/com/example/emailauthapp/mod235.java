package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod235 extends AppCompatActivity {
    PDFView cps_mod5;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod235);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView235);
        this.cps_mod5 = pDFView;
        pDFView.fromAsset("cps_mod5.pdf").load();
    }
}
