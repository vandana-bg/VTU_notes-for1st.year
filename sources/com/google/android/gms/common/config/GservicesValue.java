package com.google.android.gms.common.config;

import android.content.Context;
import android.os.Binder;
import android.os.StrictMode;
import android.util.Log;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class GservicesValue<T> {
    private static final Object sLock = new Object();
    private static zza zzby = null;
    private static int zzbz = 0;
    private static Context zzca;
    private static HashSet<String> zzcb;
    protected final String mKey;
    protected final T zzcc;
    private T zzcd = null;

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zza(String str, Float f);

        Integer zza(String str, Integer num);
    }

    public static boolean isInitialized() {
        synchronized (sLock) {
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract T zzd(String str);

    private static boolean zzi() {
        synchronized (sLock) {
        }
        return false;
    }

    protected GservicesValue(String str, T t) {
        this.mKey = str;
        this.zzcc = t;
    }

    public void override(T t) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzcd = t;
        synchronized (sLock) {
            zzi();
        }
    }

    public void resetOverride() {
        this.zzcd = null;
    }

    public final T get() {
        long clearCallingIdentity;
        T t = this.zzcd;
        if (t != null) {
            return t;
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        Object obj = sLock;
        synchronized (obj) {
        }
        synchronized (obj) {
            zzcb = null;
            zzca = null;
        }
        try {
            T zzd = zzd(this.mKey);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return zzd;
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            T zzd2 = zzd(this.mKey);
            Binder.restoreCallingIdentity(clearCallingIdentity);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return zzd2;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    public static GservicesValue<Boolean> value(String str, boolean z) {
        return new zza(str, Boolean.valueOf(z));
    }

    public static GservicesValue<Long> value(String str, Long l) {
        return new zzc(str, l);
    }

    public static GservicesValue<Integer> value(String str, Integer num) {
        return new zzb(str, num);
    }

    public static GservicesValue<Float> value(String str, Float f) {
        return new zze(str, f);
    }

    public static GservicesValue<String> value(String str, String str2) {
        return new zzd(str, str2);
    }
}
