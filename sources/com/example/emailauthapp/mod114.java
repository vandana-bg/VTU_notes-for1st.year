package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod114 extends AppCompatActivity {
    PDFView mat_mod44;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod114);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView114);
        this.mat_mod44 = pDFView;
        pDFView.fromAsset("mat_mod44.pdf").load();
    }
}
