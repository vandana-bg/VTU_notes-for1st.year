package com.github.barteksc.pdfviewer.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
    private FileUtils() {
    }

    public static File fileFromAsset(Context context, String assetName) throws IOException {
        File cacheDir = context.getCacheDir();
        File outFile = new File(cacheDir, assetName + "-pdfview.pdf");
        if (assetName.contains("/")) {
            outFile.getParentFile().mkdirs();
        }
        copy(context.getAssets().open(assetName), outFile);
        return outFile;
    }

    public static void copy(InputStream inputStream, File output) throws IOException {
        OutputStream outputStream = null;
        try {
            OutputStream outputStream2 = new FileOutputStream(output);
            byte[] bytes = new byte[1024];
            while (true) {
                int read = inputStream.read(bytes);
                int read2 = read;
                if (read == -1) {
                    break;
                }
                outputStream2.write(bytes, 0, read2);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th) {
                    outputStream2.close();
                    throw th;
                }
            }
            outputStream2.close();
        } catch (Throwable th2) {
            if (outputStream != null) {
                outputStream.close();
            }
            throw th2;
        }
    }
}
