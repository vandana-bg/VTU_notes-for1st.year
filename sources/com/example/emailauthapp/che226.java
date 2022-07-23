package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class che226 extends AppCompatActivity {
    PDFView civ_tb;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_che226);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView226);
        this.civ_tb = pDFView;
        pDFView.fromAsset("civ_tb.pdf").defaultPage(0).enableAnnotationRendering(true).scrollHandle(new DefaultScrollHandle(this)).spacing(2).load();
    }
}
