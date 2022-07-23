package com.github.barteksc.pdfviewer.exception;

public class PageRenderingException extends Exception {
    private final int page;

    public PageRenderingException(int page2, Throwable cause) {
        super(cause);
        this.page = page2;
    }

    public int getPage() {
        return this.page;
    }
}
