package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zar;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zaaz extends zar {
    private final /* synthetic */ zaaw zagv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaaz(zaaw zaaw, Looper looper) {
        super(looper);
        this.zagv = zaaw;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zagv.zaat();
                return;
            case 2:
                this.zagv.resume();
                return;
            default:
                int i = message.what;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Unknown message id: ");
                sb.append(i);
                Log.w("GoogleApiClientImpl", sb.toString());
                return;
        }
    }
}
