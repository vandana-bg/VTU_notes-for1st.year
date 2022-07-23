package com.google.android.gms.common.config;

import com.google.android.gms.common.config.GservicesValue;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
final class zzc extends GservicesValue<Long> {
    zzc(String str, Long l) {
        super(str, l);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzd(String str) {
        GservicesValue.zza zza = null;
        return zza.getLong(this.mKey, (Long) this.zzcc);
    }
}
