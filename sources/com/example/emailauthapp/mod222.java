package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod222 extends AppCompatActivity {
    PDFView che_mod2;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod222);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView222);
        this.che_mod2 = pDFView;
        pDFView.fromAsset("che_mod2.pdf").load();
    }
}
