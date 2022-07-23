package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod253 extends AppCompatActivity {
    PDFView eme_mod3;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod253);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView253);
        this.eme_mod3 = pDFView;
        pDFView.fromAsset("eme_mod3.pdf").load();
    }
}
