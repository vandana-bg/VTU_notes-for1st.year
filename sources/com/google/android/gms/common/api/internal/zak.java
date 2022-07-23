package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.base.zar;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class zak extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected final GoogleApiAvailability zace;
    protected volatile boolean zadh;
    protected final AtomicReference<zam> zadi;
    private final Handler zadj;

    protected zak(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    /* access modifiers changed from: protected */
    public abstract void zaa(ConnectionResult connectionResult, int i);

    /* access modifiers changed from: protected */
    public abstract void zam();

    private zak(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zadi = new AtomicReference<>((Object) null);
        this.zadj = new zar(Looper.getMainLooper());
        this.zace = googleApiAvailability;
    }

    public void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, (PendingIntent) null), zaa(this.zadi.get()));
        zao();
    }

    public void onCreate(Bundle bundle) {
        zam zam;
        super.onCreate(bundle);
        if (bundle != null) {
            AtomicReference<zam> atomicReference = this.zadi;
            if (bundle.getBoolean("resolving_error", false)) {
                zam = new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
            } else {
                zam = null;
            }
            atomicReference.set(zam);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zam = this.zadi.get();
        if (zam != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zam.zap());
            bundle.putInt("failed_status", zam.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zam.getConnectionResult().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.zadh = true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r5, int r6, android.content.Intent r7) {
        /*
            r4 = this;
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r0 = r4.zadi
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zam r0 = (com.google.android.gms.common.api.internal.zam) r0
            r1 = 1
            r2 = 0
            switch(r5) {
                case 1: goto L_0x002f;
                case 2: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0060
        L_0x000f:
            com.google.android.gms.common.GoogleApiAvailability r5 = r4.zace
            android.app.Activity r6 = r4.getActivity()
            int r5 = r5.isGooglePlayServicesAvailable(r6)
            if (r5 != 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            r1 = 0
        L_0x001d:
            if (r0 != 0) goto L_0x0020
            return
        L_0x0020:
            com.google.android.gms.common.ConnectionResult r6 = r0.getConnectionResult()
            int r6 = r6.getErrorCode()
            r7 = 18
            if (r6 != r7) goto L_0x0061
            if (r5 != r7) goto L_0x0061
            return
        L_0x002f:
            r5 = -1
            if (r6 != r5) goto L_0x0033
            goto L_0x0061
        L_0x0033:
            if (r6 != 0) goto L_0x0060
            r5 = 13
            if (r7 == 0) goto L_0x0041
            java.lang.String r6 = "<<ResolutionFailureErrorDetail>>"
            int r5 = r7.getIntExtra(r6, r5)
        L_0x0041:
            com.google.android.gms.common.api.internal.zam r6 = new com.google.android.gms.common.api.internal.zam
            com.google.android.gms.common.ConnectionResult r7 = new com.google.android.gms.common.ConnectionResult
            r1 = 0
            com.google.android.gms.common.ConnectionResult r3 = r0.getConnectionResult()
            java.lang.String r3 = r3.toString()
            r7.<init>(r5, r1, r3)
            int r5 = zaa(r0)
            r6.<init>(r7, r5)
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r5 = r4.zadi
            r5.set(r6)
            r0 = r6
            r1 = 0
            goto L_0x0061
        L_0x0060:
            r1 = 0
        L_0x0061:
            if (r1 == 0) goto L_0x0067
            r4.zao()
            return
        L_0x0067:
            if (r0 == 0) goto L_0x0075
            com.google.android.gms.common.ConnectionResult r5 = r0.getConnectionResult()
            int r6 = r0.zap()
            r4.zaa(r5, r6)
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zak.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onStop() {
        super.onStop();
        this.zadh = false;
    }

    /* access modifiers changed from: protected */
    public final void zao() {
        this.zadi.set((Object) null);
        zam();
    }

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zam = new zam(connectionResult, i);
        if (this.zadi.compareAndSet((Object) null, zam)) {
            this.zadj.post(new zal(this, zam));
        }
    }

    private static int zaa(zam zam) {
        if (zam == null) {
            return -1;
        }
        return zam.zap();
    }
}
