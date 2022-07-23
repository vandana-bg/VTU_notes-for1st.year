package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod115 extends AppCompatActivity {
    PDFView mat_mod55;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod115);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView115);
        this.mat_mod55 = pDFView;
        pDFView.fromAsset("mat_mod55.pdf").load();
    }
}
