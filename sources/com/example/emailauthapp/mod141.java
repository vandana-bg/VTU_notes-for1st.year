package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod141 extends AppCompatActivity {
    PDFView civ_mod1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod141);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView141);
        this.civ_mod1 = pDFView;
        pDFView.fromAsset("civ_mod1.pdf").load();
    }
}