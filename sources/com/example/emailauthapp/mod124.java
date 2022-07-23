package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod124 extends AppCompatActivity {
    PDFView phy_mod4;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod124);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView124);
        this.phy_mod4 = pDFView;
        pDFView.fromAsset("phy_mod4.pdf").load();
    }
}
