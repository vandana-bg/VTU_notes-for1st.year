package com.github.barteksc.pdfviewer;

import android.content.Context;
import android.os.AsyncTask;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

class DecodingAsyncTask extends AsyncTask<Void, Void, Throwable> {
    private boolean cancelled = false;
    private Context context;
    private DocumentSource docSource;
    private int firstPageIdx;
    private int pageHeight;
    private int pageWidth;
    private String password;
    private PdfDocument pdfDocument;
    private PDFView pdfView;
    private PdfiumCore pdfiumCore;

    DecodingAsyncTask(DocumentSource docSource2, String password2, PDFView pdfView2, PdfiumCore pdfiumCore2, int firstPageIdx2) {
        this.docSource = docSource2;
        this.firstPageIdx = firstPageIdx2;
        this.pdfView = pdfView2;
        this.password = password2;
        this.pdfiumCore = pdfiumCore2;
        this.context = pdfView2.getContext();
    }

    /* access modifiers changed from: protected */
    public Throwable doInBackground(Void... params) {
        try {
            PdfDocument createDocument = this.docSource.createDocument(this.context, this.pdfiumCore, this.password);
            this.pdfDocument = createDocument;
            this.pdfiumCore.openPage(createDocument, this.firstPageIdx);
            this.pageWidth = this.pdfiumCore.getPageWidth(this.pdfDocument, this.firstPageIdx);
            this.pageHeight = this.pdfiumCore.getPageHeight(this.pdfDocument, this.firstPageIdx);
            return null;
        } catch (Throwable t) {
            return t;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Throwable t) {
        if (t != null) {
            this.pdfView.loadError(t);
        } else if (!this.cancelled) {
            this.pdfView.loadComplete(this.pdfDocument, this.pageWidth, this.pageHeight);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        this.cancelled = true;
    }
}
