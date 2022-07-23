package com.google.android.gms.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class zza {
    private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzhl;
    private static float zzhm = Float.NaN;

    public static int zzh(Context context) {
        boolean z;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
        int i = 0;
        int intExtra = registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0);
        int i2 = 3;
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            i2 = 7;
        }
        int i3 = (intExtra & i2) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            z = powerManager.isInteractive();
        } else {
            z = powerManager.isScreenOn();
        }
        if (z) {
            i = 2;
        }
        return i | i3;
    }

    public static synchronized float zzi(Context context) {
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzhl >= 60000 || Float.isNaN(zzhm)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, filter);
                if (registerReceiver != null) {
                    zzhm = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzhl = SystemClock.elapsedRealtime();
                float f = zzhm;
                return f;
            }
            float f2 = zzhm;
            return f2;
        }
    }
}
