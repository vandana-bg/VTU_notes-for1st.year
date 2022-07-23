package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
final /* synthetic */ class zze implements Callable {
    private final boolean zzad;
    private final String zzae;
    private final zzd zzaf;

    zze(boolean z, String str, zzd zzd) {
        this.zzad = z;
        this.zzae = str;
        this.zzaf = zzd;
    }

    public final Object call() {
        return zzc.zza(this.zzad, this.zzae, this.zzaf);
    }
}
