package com.google.android.gms.dynamic;

import android.os.Bundle;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zaa implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zart;

    zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zart = deferredLifecycleHelper;
    }

    public final void onDelegateCreated(T t) {
        LifecycleDelegate unused = this.zart.zaru = t;
        Iterator it = this.zart.zarw.iterator();
        while (it.hasNext()) {
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(this.zart.zaru);
        }
        this.zart.zarw.clear();
        Bundle unused2 = this.zart.zarv = null;
    }
}
