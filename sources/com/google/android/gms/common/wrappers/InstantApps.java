package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class InstantApps {
    private static Context zzim;
    private static Boolean zzin;

    public static synchronized boolean isInstantApp(Context context) {
        Boolean bool;
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            Context context2 = zzim;
            if (context2 == null || (bool = zzin) == null || context2 != applicationContext) {
                zzin = null;
                if (PlatformVersion.isAtLeastO()) {
                    zzin = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzin = true;
                    } catch (ClassNotFoundException e) {
                        zzin = false;
                    }
                }
                zzim = applicationContext;
                boolean booleanValue = zzin.booleanValue();
                return booleanValue;
            }
            boolean booleanValue2 = bool.booleanValue();
            return booleanValue2;
        }
    }
}
