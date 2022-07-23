package com.google.android.gms.common.internal;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class GmsLogger {
    private static final int zzev = 15;
    private static final String zzew = null;
    private final String zzex;
    private final String zzey;

    public GmsLogger(String str, String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.zzex = str;
        if (str2 == null || str2.length() <= 0) {
            this.zzey = null;
        } else {
            this.zzey = str2;
        }
    }

    public GmsLogger(String str) {
        this(str, (String) null);
    }

    public final boolean canLog(int i) {
        return Log.isLoggable(this.zzex, i);
    }

    public final boolean canLogPii() {
        return false;
    }

    /* renamed from: d */
    public final void mo12286d(String str, String str2) {
        if (canLog(3)) {
            Log.d(str, zzh(str2));
        }
    }

    /* renamed from: d */
    public final void mo12287d(String str, String str2, Throwable th) {
        if (canLog(3)) {
            Log.d(str, zzh(str2), th);
        }
    }

    /* renamed from: v */
    public final void mo12295v(String str, String str2) {
        if (canLog(2)) {
            Log.v(str, zzh(str2));
        }
    }

    /* renamed from: v */
    public final void mo12296v(String str, String str2, Throwable th) {
        if (canLog(2)) {
            Log.v(str, zzh(str2), th);
        }
    }

    /* renamed from: i */
    public final void mo12291i(String str, String str2) {
        if (canLog(4)) {
            Log.i(str, zzh(str2));
        }
    }

    /* renamed from: i */
    public final void mo12292i(String str, String str2, Throwable th) {
        if (canLog(4)) {
            Log.i(str, zzh(str2), th);
        }
    }

    /* renamed from: w */
    public final void mo12297w(String str, String str2) {
        if (canLog(5)) {
            Log.w(str, zzh(str2));
        }
    }

    /* renamed from: w */
    public final void mo12298w(String str, String str2, Throwable th) {
        if (canLog(5)) {
            Log.w(str, zzh(str2), th);
        }
    }

    public final void wfmt(String str, String str2, Object... objArr) {
        if (canLog(5)) {
            Log.w(this.zzex, zza(str2, objArr));
        }
    }

    /* renamed from: e */
    public final void mo12288e(String str, String str2) {
        if (canLog(6)) {
            Log.e(str, zzh(str2));
        }
    }

    /* renamed from: e */
    public final void mo12289e(String str, String str2, Throwable th) {
        if (canLog(6)) {
            Log.e(str, zzh(str2), th);
        }
    }

    public final void efmt(String str, String str2, Object... objArr) {
        if (canLog(6)) {
            Log.e(str, zza(str2, objArr));
        }
    }

    public final void wtf(String str, String str2, Throwable th) {
        if (canLog(7)) {
            Log.e(str, zzh(str2), th);
            Log.wtf(str, zzh(str2), th);
        }
    }

    public final void pii(String str, String str2) {
        if (canLogPii()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(" PII_LOG");
            Log.i(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), zzh(str2));
        }
    }

    public final void pii(String str, String str2, Throwable th) {
        if (canLogPii()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(" PII_LOG");
            Log.i(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), zzh(str2), th);
        }
    }

    private final String zzh(String str) {
        String str2 = this.zzey;
        if (str2 == null) {
            return str;
        }
        return str2.concat(str);
    }

    private final String zza(String str, Object... objArr) {
        String format = String.format(str, objArr);
        String str2 = this.zzey;
        if (str2 == null) {
            return format;
        }
        return str2.concat(format);
    }
}
