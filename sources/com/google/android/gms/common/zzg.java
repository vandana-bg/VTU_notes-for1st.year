package com.google.android.gms.common;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
final class zzg extends zzd {
    private final byte[] zzai;

    zzg(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zzai = bArr;
    }

    /* access modifiers changed from: package-private */
    public final byte[] getBytes() {
        return this.zzai;
    }
}
