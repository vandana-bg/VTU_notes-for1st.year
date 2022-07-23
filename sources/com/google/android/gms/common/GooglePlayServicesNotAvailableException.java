package com.google.android.gms.common;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class GooglePlayServicesNotAvailableException extends Exception {
    public final int errorCode;

    public GooglePlayServicesNotAvailableException(int i) {
        this.errorCode = i;
    }
}
