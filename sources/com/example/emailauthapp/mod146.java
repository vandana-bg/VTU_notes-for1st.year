package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class mod146 extends AppCompatActivity {
    PDFView che_tb;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod146);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView146);
        this.che_tb = pDFView;
        pDFView.fromAsset("che_tb.pdf").defaultPage(0).enableAnnotationRendering(true).scrollHandle(new DefaultScrollHandle(this)).spacing(2).load();
    }
}
