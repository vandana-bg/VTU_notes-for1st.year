package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public abstract class BaseGmsClient<T extends IInterface> {
    public static final int CONNECT_STATE_CONNECTED = 4;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zzch = new Feature[0];
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzci;
    private long zzcj;
    private long zzck;
    private int zzcl;
    private long zzcm;
    private zzh zzcn;
    private final Looper zzco;
    private final GmsClientSupervisor zzcp;
    private final GoogleApiAvailabilityLight zzcq;
    /* access modifiers changed from: private */
    public final Object zzcr;
    /* access modifiers changed from: private */
    public IGmsServiceBroker zzcs;
    protected ConnectionProgressReportCallbacks zzct;
    private T zzcu;
    /* access modifiers changed from: private */
    public final ArrayList<zzb<?>> zzcv;
    private zze zzcw;
    private int zzcx;
    /* access modifiers changed from: private */
    public final BaseConnectionCallbacks zzcy;
    /* access modifiers changed from: private */
    public final BaseOnConnectionFailedListener zzcz;
    private final int zzda;
    private final String zzdb;
    /* access modifiers changed from: private */
    public ConnectionResult zzdc;
    /* access modifiers changed from: private */
    public boolean zzdd;
    private volatile zza zzde;
    protected AtomicInteger zzdf;

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface BaseConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public LegacyClientCallbackAdapter() {
        }

        public void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService((IAccountAccessor) null, baseGmsClient.getScopes());
            } else if (BaseGmsClient.this.zzcz != null) {
                BaseGmsClient.this.zzcz.onConnectionFailed(connectionResult);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface SignOutCallbacks {
        void onSignOutComplete();
    }

    protected BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    /* access modifiers changed from: protected */
    public abstract T createServiceInterface(IBinder iBinder);

    /* access modifiers changed from: protected */
    public abstract String getServiceDescriptor();

    /* access modifiers changed from: protected */
    public abstract String getStartServiceAction();

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    final class zzc extends zzi {
        public zzc(Looper looper) {
            super(looper);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.app.PendingIntent} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void handleMessage(android.os.Message r8) {
            /*
                r7 = this;
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                java.util.concurrent.atomic.AtomicInteger r0 = r0.zzdf
                int r0 = r0.get()
                int r1 = r8.arg1
                if (r0 == r1) goto L_0x0016
                boolean r0 = zzb(r8)
                if (r0 == 0) goto L_0x0015
                zza(r8)
            L_0x0015:
                return
            L_0x0016:
                int r0 = r8.what
                r1 = 4
                r2 = 1
                r3 = 5
                if (r0 == r2) goto L_0x0032
                int r0 = r8.what
                r4 = 7
                if (r0 == r4) goto L_0x0032
                int r0 = r8.what
                if (r0 != r1) goto L_0x002e
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r0 = r0.enableLocalFallback()
                if (r0 == 0) goto L_0x0032
            L_0x002e:
                int r0 = r8.what
                if (r0 != r3) goto L_0x003e
            L_0x0032:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r0 = r0.isConnecting()
                if (r0 != 0) goto L_0x003e
                zza(r8)
                return
            L_0x003e:
                int r0 = r8.what
                r4 = 8
                r5 = 3
                r6 = 0
                if (r0 != r1) goto L_0x0089
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r1 = new com.google.android.gms.common.ConnectionResult
                int r8 = r8.arg2
                r1.<init>(r8)
                com.google.android.gms.common.ConnectionResult unused = r0.zzdc = r1
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r8 = r8.zzn()
                if (r8 == 0) goto L_0x0068
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r8 = r8.zzdd
                if (r8 != 0) goto L_0x0068
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                r8.zza((int) r5, null)
                return
            L_0x0068:
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzdc
                if (r8 == 0) goto L_0x0077
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzdc
                goto L_0x007c
            L_0x0077:
                com.google.android.gms.common.ConnectionResult r8 = new com.google.android.gms.common.ConnectionResult
                r8.<init>(r4)
            L_0x007c:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r0 = r0.zzct
                r0.onReportServiceBinding(r8)
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                r0.onConnectionFailed(r8)
                return
            L_0x0089:
                int r0 = r8.what
                if (r0 != r3) goto L_0x00ae
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzdc
                if (r8 == 0) goto L_0x009c
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.ConnectionResult r8 = r8.zzdc
                goto L_0x00a1
            L_0x009c:
                com.google.android.gms.common.ConnectionResult r8 = new com.google.android.gms.common.ConnectionResult
                r8.<init>(r4)
            L_0x00a1:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r0 = r0.zzct
                r0.onReportServiceBinding(r8)
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                r0.onConnectionFailed(r8)
                return
            L_0x00ae:
                int r0 = r8.what
                if (r0 != r5) goto L_0x00d1
                java.lang.Object r0 = r8.obj
                boolean r0 = r0 instanceof android.app.PendingIntent
                if (r0 == 0) goto L_0x00bd
                java.lang.Object r0 = r8.obj
                r6 = r0
                android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            L_0x00bd:
                com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult
                int r8 = r8.arg2
                r0.<init>(r8, r6)
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks r8 = r8.zzct
                r8.onReportServiceBinding(r0)
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                r8.onConnectionFailed(r0)
                return
            L_0x00d1:
                int r0 = r8.what
                r1 = 6
                if (r0 != r1) goto L_0x00fb
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                r0.zza((int) r3, null)
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r0 = r0.zzcy
                if (r0 == 0) goto L_0x00ee
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r0 = r0.zzcy
                int r1 = r8.arg2
                r0.onConnectionSuspended(r1)
            L_0x00ee:
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                int r8 = r8.arg2
                r0.onConnectionSuspended(r8)
                com.google.android.gms.common.internal.BaseGmsClient r8 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean unused = r8.zza((int) r3, (int) r2, r6)
                return
            L_0x00fb:
                int r0 = r8.what
                r1 = 2
                if (r0 != r1) goto L_0x010c
                com.google.android.gms.common.internal.BaseGmsClient r0 = com.google.android.gms.common.internal.BaseGmsClient.this
                boolean r0 = r0.isConnected()
                if (r0 != 0) goto L_0x010c
                zza(r8)
                return
            L_0x010c:
                boolean r0 = zzb(r8)
                if (r0 == 0) goto L_0x011a
                java.lang.Object r8 = r8.obj
                com.google.android.gms.common.internal.BaseGmsClient$zzb r8 = (com.google.android.gms.common.internal.BaseGmsClient.zzb) r8
                r8.zzo()
                return
            L_0x011a:
                int r8 = r8.what
                r0 = 45
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.String r0 = "Don't know how to handle message: "
                r1.append(r0)
                r1.append(r8)
                java.lang.String r8 = r1.toString()
                java.lang.Exception r0 = new java.lang.Exception
                r0.<init>()
                java.lang.String r1 = "GmsClient"
                android.util.Log.wtf(r1, r8, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zzc.handleMessage(android.os.Message):void");
        }

        private static void zza(Message message) {
            zzb zzb = (zzb) message.obj;
            zzb.zzk();
            zzb.unregister();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public final class zze implements ServiceConnection {
        private final int zzdj;

        public zze(int i) {
            this.zzdj = i;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGmsServiceBroker iGmsServiceBroker;
            if (iBinder == null) {
                BaseGmsClient.this.zzb(16);
                return;
            }
            synchronized (BaseGmsClient.this.zzcr) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                if (iBinder == null) {
                    iGmsServiceBroker = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        iGmsServiceBroker = new IGmsServiceBroker.Stub.zza(iBinder);
                    } else {
                        iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                IGmsServiceBroker unused = baseGmsClient.zzcs = iGmsServiceBroker;
            }
            BaseGmsClient.this.zza(0, (Bundle) null, this.zzdj);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzcr) {
                IGmsServiceBroker unused = BaseGmsClient.this.zzcs = null;
            }
            BaseGmsClient.this.mHandler.sendMessage(BaseGmsClient.this.mHandler.obtainMessage(6, this.zzdj, 1));
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected final class zzf extends zza {
        public zzf(int i, Bundle bundle) {
            super(i, (Bundle) null);
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            if (!BaseGmsClient.this.enableLocalFallback() || !BaseGmsClient.this.zzn()) {
                BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult);
                BaseGmsClient.this.onConnectionFailed(connectionResult);
                return;
            }
            BaseGmsClient.this.zzb(16);
        }

        /* access modifiers changed from: protected */
        public final boolean zzj() {
            BaseGmsClient.this.zzct.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected abstract class zzb<TListener> {
        private TListener zzdg;
        private boolean zzdh = false;

        public zzb(TListener tlistener) {
            this.zzdg = tlistener;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(TListener tlistener);

        /* access modifiers changed from: protected */
        public abstract void zzk();

        public final void zzo() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.zzdg;
                if (this.zzdh) {
                    String valueOf = String.valueOf(this);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    sb.append("Callback proxy ");
                    sb.append(valueOf);
                    sb.append(" being reused. This is not safe.");
                    Log.w("GmsClient", sb.toString());
                }
            }
            if (tlistener != null) {
                try {
                    zza(tlistener);
                } catch (RuntimeException e) {
                    zzk();
                    throw e;
                }
            } else {
                zzk();
            }
            synchronized (this) {
                this.zzdh = true;
            }
            unregister();
        }

        public final void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.zzcv) {
                BaseGmsClient.this.zzcv.remove(this);
            }
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzdg = null;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public static final class zzd extends IGmsCallbacks.zza {
        private BaseGmsClient zzdi;
        private final int zzdj;

        public zzd(BaseGmsClient baseGmsClient, int i) {
            this.zzdi = baseGmsClient;
            this.zzdj = i;
        }

        public final void zza(int i, Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
            Preconditions.checkNotNull(this.zzdi, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzdi.onPostInitHandler(i, iBinder, bundle, this.zzdj);
            this.zzdi = null;
        }

        public final void zza(int i, IBinder iBinder, zza zza) {
            Preconditions.checkNotNull(this.zzdi, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zza);
            this.zzdi.zza(zza);
            onPostInitComplete(i, iBinder, zza.zzdm);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    protected final class zzg extends zza {
        private final IBinder zzdk;

        public zzg(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzdk = iBinder;
        }

        /* access modifiers changed from: protected */
        public final void zza(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzcz != null) {
                BaseGmsClient.this.zzcz.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        /* access modifiers changed from: protected */
        public final boolean zzj() {
            try {
                String interfaceDescriptor = this.zzdk.getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                    StringBuilder sb = new StringBuilder(String.valueOf(serviceDescriptor).length() + 34 + String.valueOf(interfaceDescriptor).length());
                    sb.append("service descriptor mismatch: ");
                    sb.append(serviceDescriptor);
                    sb.append(" vs. ");
                    sb.append(interfaceDescriptor);
                    Log.e("GmsClient", sb.toString());
                    return false;
                }
                IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.zzdk);
                if (createServiceInterface == null || (!BaseGmsClient.this.zza(2, 4, createServiceInterface) && !BaseGmsClient.this.zza(3, 4, createServiceInterface))) {
                    return false;
                }
                ConnectionResult unused = BaseGmsClient.this.zzdc = null;
                Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                if (BaseGmsClient.this.zzcy == null) {
                    return true;
                }
                BaseGmsClient.this.zzcy.onConnected(connectionHint);
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    private abstract class zza extends zzb<Boolean> {
        private final int statusCode;
        private final Bundle zzcf;

        protected zza(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.zzcf = bundle;
        }

        /* access modifiers changed from: protected */
        public abstract void zza(ConnectionResult connectionResult);

        /* access modifiers changed from: protected */
        public abstract boolean zzj();

        /* access modifiers changed from: protected */
        public final void zzk() {
        }

        /* JADX WARNING: type inference failed for: r5v11, types: [android.os.Parcelable] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ void zza(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                r0 = 1
                r1 = 0
                if (r5 != 0) goto L_0x000c
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                return
            L_0x000c:
                int r5 = r4.statusCode
                switch(r5) {
                    case 0: goto L_0x0055;
                    case 10: goto L_0x0025;
                    default: goto L_0x0011;
                }
            L_0x0011:
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                android.os.Bundle r5 = r4.zzcf
                if (r5 == 0) goto L_0x006b
                java.lang.String r0 = "pendingIntent"
                android.os.Parcelable r5 = r5.getParcelable(r0)
                r1 = r5
                android.app.PendingIntent r1 = (android.app.PendingIntent) r1
                goto L_0x006b
            L_0x0025:
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                r1 = 3
                java.lang.Object[] r1 = new java.lang.Object[r1]
                r2 = 0
                java.lang.Class r3 = r4.getClass()
                java.lang.String r3 = r3.getSimpleName()
                r1[r2] = r3
                com.google.android.gms.common.internal.BaseGmsClient r2 = com.google.android.gms.common.internal.BaseGmsClient.this
                java.lang.String r2 = r2.getStartServiceAction()
                r1[r0] = r2
                r0 = 2
                com.google.android.gms.common.internal.BaseGmsClient r2 = com.google.android.gms.common.internal.BaseGmsClient.this
                java.lang.String r2 = r2.getServiceDescriptor()
                r1[r0] = r2
                java.lang.String r0 = "A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. "
                java.lang.String r0 = java.lang.String.format(r0, r1)
                r5.<init>(r0)
                throw r5
            L_0x0055:
                boolean r5 = r4.zzj()
                if (r5 != 0) goto L_0x0075
                com.google.android.gms.common.internal.BaseGmsClient r5 = com.google.android.gms.common.internal.BaseGmsClient.this
                r5.zza((int) r0, null)
                com.google.android.gms.common.ConnectionResult r5 = new com.google.android.gms.common.ConnectionResult
                r0 = 8
                r5.<init>(r0, r1)
                r4.zza((com.google.android.gms.common.ConnectionResult) r5)
                return
            L_0x006b:
                com.google.android.gms.common.ConnectionResult r5 = new com.google.android.gms.common.ConnectionResult
                int r0 = r4.statusCode
                r5.<init>(r0, r1)
                r4.zza((com.google.android.gms.common.ConnectionResult) r5)
            L_0x0075:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.BaseGmsClient.zza.zza(java.lang.Object):void");
        }
    }

    protected BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcr = new Object();
        this.zzcv = new ArrayList<>();
        this.zzcx = 1;
        this.zzdc = null;
        this.zzdd = false;
        this.zzde = null;
        this.zzdf = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzco = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzcp = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcq = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzc(looper);
        this.zzda = i;
        this.zzcy = baseConnectionCallbacks;
        this.zzcz = baseOnConnectionFailedListener;
        this.zzdb = str;
    }

    protected BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcr = new Object();
        this.zzcv = new ArrayList<>();
        this.zzcx = 1;
        this.zzdc = null;
        this.zzdd = false;
        this.zzde = null;
        this.zzdf = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzco = handler.getLooper();
        this.zzcp = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcq = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzda = i;
        this.zzcy = baseConnectionCallbacks;
        this.zzcz = baseOnConnectionFailedListener;
        this.zzdb = null;
    }

    /* access modifiers changed from: protected */
    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    private final String zzl() {
        String str = this.zzdb;
        return str == null ? this.mContext.getClass().getName() : str;
    }

    /* access modifiers changed from: protected */
    public String getLocalStartServiceAction() {
        return null;
    }

    /* access modifiers changed from: private */
    public final void zza(zza zza2) {
        this.zzde = zza2;
    }

    public final Feature[] getAvailableFeatures() {
        zza zza2 = this.zzde;
        if (zza2 == null) {
            return null;
        }
        return zza2.zzdn;
    }

    /* access modifiers changed from: protected */
    public void onConnectedLocked(T t) {
        this.zzck = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onConnectionSuspended(int i) {
        this.zzci = i;
        this.zzcj = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzcl = connectionResult.getErrorCode();
        this.zzcm = System.currentTimeMillis();
    }

    /* access modifiers changed from: private */
    public final void zza(int i, T t) {
        zzh zzh;
        zzh zzh2;
        boolean z = true;
        if ((i == 4) != (t != null)) {
            z = false;
        }
        Preconditions.checkArgument(z);
        synchronized (this.mLock) {
            this.zzcx = i;
            this.zzcu = t;
            onSetConnectState(i, t);
            switch (i) {
                case 1:
                    if (this.zzcw != null) {
                        this.zzcp.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcw, zzl());
                        this.zzcw = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    if (!(this.zzcw == null || (zzh2 = this.zzcn) == null)) {
                        String zzt = zzh2.zzt();
                        String packageName = this.zzcn.getPackageName();
                        StringBuilder sb = new StringBuilder(String.valueOf(zzt).length() + 70 + String.valueOf(packageName).length());
                        sb.append("Calling connect() while still connected, missing disconnect() for ");
                        sb.append(zzt);
                        sb.append(" on ");
                        sb.append(packageName);
                        Log.e("GmsClient", sb.toString());
                        this.zzcp.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcw, zzl());
                        this.zzdf.incrementAndGet();
                    }
                    this.zzcw = new zze(this.zzdf.get());
                    if (this.zzcx != 3 || getLocalStartServiceAction() == null) {
                        zzh = new zzh(getStartServicePackage(), getStartServiceAction(), false, 129, getUseDynamicLookup());
                    } else {
                        zzh = new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129, false);
                    }
                    this.zzcn = zzh;
                    if (!this.zzcp.zza(new GmsClientSupervisor.zza(zzh.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcn.getUseDynamicLookup()), this.zzcw, zzl())) {
                        String zzt2 = this.zzcn.zzt();
                        String packageName2 = this.zzcn.getPackageName();
                        StringBuilder sb2 = new StringBuilder(String.valueOf(zzt2).length() + 34 + String.valueOf(packageName2).length());
                        sb2.append("unable to connect to service: ");
                        sb2.append(zzt2);
                        sb2.append(" on ");
                        sb2.append(packageName2);
                        Log.e("GmsClient", sb2.toString());
                        zza(16, (Bundle) null, this.zzdf.get());
                    }
                    break;
                case 4:
                    onConnectedLocked(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetConnectState(int i, T t) {
    }

    /* access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        synchronized (this.mLock) {
            if (this.zzcx != i) {
                return false;
            }
            zza(i2, t);
            return true;
        }
    }

    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzcq.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (IInterface) null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, (PendingIntent) null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzct = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, (IInterface) null);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcx == 4;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            int i = this.zzcx;
            if (i != 2) {
                if (i != 3) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    private final boolean zzm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcx == 3;
        }
        return z;
    }

    public void disconnect() {
        this.zzdf.incrementAndGet();
        synchronized (this.zzcv) {
            int size = this.zzcv.size();
            for (int i = 0; i < size; i++) {
                this.zzcv.get(i).removeListener();
            }
            this.zzcv.clear();
        }
        synchronized (this.zzcr) {
            this.zzcs = null;
        }
        zza(1, (IInterface) null);
    }

    public void triggerConnectionSuspended(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(6, this.zzdf.get(), i));
    }

    /* access modifiers changed from: private */
    public final void zzb(int i) {
        int i2;
        if (zzm()) {
            i2 = 5;
            this.zzdd = true;
        } else {
            i2 = 4;
        }
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(i2, this.zzdf.get(), 16));
    }

    /* access modifiers changed from: protected */
    public void triggerNotAvailable(ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, PendingIntent pendingIntent) {
        this.zzct = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(3, this.zzdf.get(), i, pendingIntent));
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzco;
    }

    public Account getAccount() {
        return null;
    }

    public Feature[] getApiFeatures() {
        return zzch;
    }

    /* access modifiers changed from: protected */
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    /* access modifiers changed from: protected */
    public void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new zzg(i, iBinder, bundle)));
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(7, i2, -1, new zzf(i, (Bundle) null)));
    }

    /* access modifiers changed from: protected */
    public final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public Bundle getConnectionHint() {
        return null;
    }

    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzcx != 5) {
                checkConnected();
                Preconditions.checkState(this.zzcu != null, "Client is connected but service is null");
                t = this.zzcu;
            } else {
                throw new DeadObjectException();
            }
        }
        return t;
    }

    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzda);
        getServiceRequest.zzak = this.mContext.getPackageName();
        getServiceRequest.zzdt = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzds = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdu = getAccount() != null ? getAccount() : new Account("<<default account>>", AccountType.GOOGLE);
            if (iAccountAccessor != null) {
                getServiceRequest.zzdr = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdu = getAccount();
        }
        getServiceRequest.zzdv = zzch;
        getServiceRequest.zzdw = getApiFeatures();
        try {
            synchronized (this.zzcr) {
                IGmsServiceBroker iGmsServiceBroker = this.zzcs;
                if (iGmsServiceBroker != null) {
                    iGmsServiceBroker.getService(new zzd(this, this.zzdf.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            onPostInitHandler(8, (IBinder) null, (Bundle) null, this.zzdf.get());
        }
    }

    /* access modifiers changed from: protected */
    public boolean enableLocalFallback() {
        return false;
    }

    public boolean requiresSignIn() {
        return false;
    }

    public void onUserSignOut(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    public boolean requiresAccount() {
        return false;
    }

    public boolean requiresGooglePlayServices() {
        return true;
    }

    public boolean providesSignIn() {
        return false;
    }

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    /* access modifiers changed from: protected */
    public Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.mLock) {
            i = this.zzcx;
            t = this.zzcu;
        }
        synchronized (this.zzcr) {
            iGmsServiceBroker = this.zzcs;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzck > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzck;
            String format = simpleDateFormat.format(new Date(this.zzck));
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 21);
            sb.append(j);
            sb.append(" ");
            sb.append(format);
            append.println(sb.toString());
        }
        if (this.zzcj > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            int i2 = this.zzci;
            switch (i2) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(i2));
                    break;
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzcj;
            String format2 = simpleDateFormat.format(new Date(this.zzcj));
            StringBuilder sb2 = new StringBuilder(String.valueOf(format2).length() + 21);
            sb2.append(j2);
            sb2.append(" ");
            sb2.append(format2);
            append2.println(sb2.toString());
        }
        if (this.zzcm > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzcl));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzcm;
            String format3 = simpleDateFormat.format(new Date(this.zzcm));
            StringBuilder sb3 = new StringBuilder(String.valueOf(format3).length() + 21);
            sb3.append(j3);
            sb3.append(" ");
            sb3.append(format3);
            append3.println(sb3.toString());
        }
    }

    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzcr) {
            IGmsServiceBroker iGmsServiceBroker = this.zzcs;
            if (iGmsServiceBroker == null) {
                return null;
            }
            IBinder asBinder = iGmsServiceBroker.asBinder();
            return asBinder;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzn() {
        if (this.zzdd || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public String getEndpointPackageName() {
        zzh zzh;
        if (isConnected() && (zzh = this.zzcn) != null) {
            return zzh.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public boolean getUseDynamicLookup() {
        return false;
    }
}
