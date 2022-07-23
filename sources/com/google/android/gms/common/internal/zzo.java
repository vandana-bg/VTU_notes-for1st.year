package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzd;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class zzo extends zzb implements zzn {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    public final boolean zza(zzj zzj, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzj);
        zzd.zza(zza, (IInterface) iObjectWrapper);
        Parcel zza2 = zza(5, zza);
        boolean zza3 = zzd.zza(zza2);
        zza2.recycle();
        return zza3;
    }
}
