package com.example.emailauthapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class mod134 extends AppCompatActivity {
    PDFView ele_mod4;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().hide();
        setContentView((int) C0681R.layout.activity_mod134);
        PDFView pDFView = (PDFView) findViewById(C0681R.C0684id.pdfView134);
        this.ele_mod4 = pDFView;
        pDFView.fromAsset("ele_mod4.pdf").load();
    }
}
