package androidx.core.location;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.p003os.ExecutorCompat;
import androidx.core.util.Preconditions;
import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class LocationManagerCompat {
    private static final long PRE_N_LOOPER_TIMEOUT_S = 4;
    private static Field sContextField;
    private static final SimpleArrayMap<Object, Object> sGnssStatusListeners = new SimpleArrayMap<>();

    public static boolean isLocationEnabled(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.isLocationEnabled(locationManager);
        }
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                if (sContextField == null) {
                    sContextField = LocationManager.class.getDeclaredField("mContext");
                }
                sContextField.setAccessible(true);
                Context context = (Context) sContextField.get(locationManager);
                if (Build.VERSION.SDK_INT != 19) {
                    return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
                }
                if (Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0) {
                    return true;
                }
                return false;
            } catch (ClassCastException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            }
        }
        if (locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback, Handler handler) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, ExecutorCompat.create(handler), callback);
        }
        return registerGnssStatusCallback(locationManager, (Executor) new InlineHandlerExecutor(handler), callback);
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, Executor executor, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, (Handler) null, executor, callback);
        }
        Looper looper = Looper.myLooper();
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        return registerGnssStatusCallback(locationManager, new Handler(looper), executor, callback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00d7, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00e3, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean registerGnssStatusCallback(android.location.LocationManager r18, android.os.Handler r19, java.util.concurrent.Executor r20, androidx.core.location.GnssStatusCompat.Callback r21) {
        /*
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 1
            r6 = 0
            r7 = 30
            if (r0 < r7) goto L_0x0032
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r7 = sGnssStatusListeners
            monitor-enter(r7)
            java.lang.Object r0 = r7.get(r4)     // Catch:{ all -> 0x002f }
            androidx.core.location.LocationManagerCompat$GnssStatusTransport r0 = (androidx.core.location.LocationManagerCompat.GnssStatusTransport) r0     // Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x0022
            androidx.core.location.LocationManagerCompat$GnssStatusTransport r8 = new androidx.core.location.LocationManagerCompat$GnssStatusTransport     // Catch:{ all -> 0x002f }
            r8.<init>(r4)     // Catch:{ all -> 0x002f }
            r0 = r8
        L_0x0022:
            boolean r8 = r1.registerGnssStatusCallback(r3, r0)     // Catch:{ all -> 0x002f }
            if (r8 == 0) goto L_0x002d
            r7.put(r4, r0)     // Catch:{ all -> 0x002f }
            monitor-exit(r7)     // Catch:{ all -> 0x002f }
            return r5
        L_0x002d:
            monitor-exit(r7)     // Catch:{ all -> 0x002f }
            return r6
        L_0x002f:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x002f }
            throw r0
        L_0x0032:
            int r0 = android.os.Build.VERSION.SDK_INT
            r7 = 24
            if (r0 < r7) goto L_0x0069
            if (r2 == 0) goto L_0x003c
            r0 = 1
            goto L_0x003d
        L_0x003c:
            r0 = 0
        L_0x003d:
            androidx.core.util.Preconditions.checkArgument(r0)
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r7 = sGnssStatusListeners
            monitor-enter(r7)
            java.lang.Object r0 = r7.get(r4)     // Catch:{ all -> 0x0066 }
            androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport r0 = (androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport) r0     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x0053
            androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport r8 = new androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport     // Catch:{ all -> 0x0066 }
            r8.<init>(r4)     // Catch:{ all -> 0x0066 }
            r0 = r8
            goto L_0x0056
        L_0x0053:
            r0.unregister()     // Catch:{ all -> 0x0066 }
        L_0x0056:
            r0.register(r3)     // Catch:{ all -> 0x0066 }
            boolean r8 = r1.registerGnssStatusCallback(r0, r2)     // Catch:{ all -> 0x0066 }
            if (r8 == 0) goto L_0x0064
            r7.put(r4, r0)     // Catch:{ all -> 0x0066 }
            monitor-exit(r7)     // Catch:{ all -> 0x0066 }
            return r5
        L_0x0064:
            monitor-exit(r7)     // Catch:{ all -> 0x0066 }
            return r6
        L_0x0066:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0066 }
            throw r0
        L_0x0069:
            if (r2 == 0) goto L_0x006d
            r0 = 1
            goto L_0x006e
        L_0x006d:
            r0 = 0
        L_0x006e:
            androidx.core.util.Preconditions.checkArgument(r0)
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r7 = sGnssStatusListeners
            monitor-enter(r7)
            java.lang.Object r0 = r7.get(r4)     // Catch:{ all -> 0x014e }
            androidx.core.location.LocationManagerCompat$GpsStatusTransport r0 = (androidx.core.location.LocationManagerCompat.GpsStatusTransport) r0     // Catch:{ all -> 0x014e }
            if (r0 != 0) goto L_0x0084
            androidx.core.location.LocationManagerCompat$GpsStatusTransport r8 = new androidx.core.location.LocationManagerCompat$GpsStatusTransport     // Catch:{ all -> 0x014e }
            r8.<init>(r1, r4)     // Catch:{ all -> 0x014e }
            r0 = r8
            goto L_0x0088
        L_0x0084:
            r0.unregister()     // Catch:{ all -> 0x014e }
            r8 = r0
        L_0x0088:
            r8.register(r3)     // Catch:{ all -> 0x014e }
            r9 = r8
            java.util.concurrent.FutureTask r0 = new java.util.concurrent.FutureTask     // Catch:{ all -> 0x014e }
            androidx.core.location.LocationManagerCompat$1 r10 = new androidx.core.location.LocationManagerCompat$1     // Catch:{ all -> 0x014e }
            r10.<init>(r1, r9)     // Catch:{ all -> 0x014e }
            r0.<init>(r10)     // Catch:{ all -> 0x014e }
            r10 = r0
            android.os.Looper r0 = android.os.Looper.myLooper()     // Catch:{ all -> 0x014e }
            android.os.Looper r11 = r19.getLooper()     // Catch:{ all -> 0x014e }
            if (r0 != r11) goto L_0x00a5
            r10.run()     // Catch:{ all -> 0x014e }
            goto L_0x00ab
        L_0x00a5:
            boolean r0 = r2.post(r10)     // Catch:{ all -> 0x014e }
            if (r0 == 0) goto L_0x0137
        L_0x00ab:
            r11 = 0
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ ExecutionException -> 0x0107, TimeoutException -> 0x00ef }
            r12 = 4
            long r12 = r0.toNanos(r12)     // Catch:{ ExecutionException -> 0x0107, TimeoutException -> 0x00ef }
            long r14 = java.lang.System.nanoTime()     // Catch:{ ExecutionException -> 0x0107, TimeoutException -> 0x00ef }
            long r14 = r14 + r12
        L_0x00b9:
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x00e4 }
            java.lang.Object r0 = r10.get(r12, r0)     // Catch:{ InterruptedException -> 0x00e4 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ InterruptedException -> 0x00e4 }
            boolean r0 = r0.booleanValue()     // Catch:{ InterruptedException -> 0x00e4 }
            if (r0 == 0) goto L_0x00d8
            androidx.collection.SimpleArrayMap<java.lang.Object, java.lang.Object> r0 = sGnssStatusListeners     // Catch:{ InterruptedException -> 0x00e4 }
            r0.put(r4, r9)     // Catch:{ InterruptedException -> 0x00e4 }
            if (r11 == 0) goto L_0x00d6
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x014e }
            r0.interrupt()     // Catch:{ all -> 0x014e }
        L_0x00d6:
            monitor-exit(r7)     // Catch:{ all -> 0x014e }
            return r5
        L_0x00d8:
            if (r11 == 0) goto L_0x00e2
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x014e }
            r0.interrupt()     // Catch:{ all -> 0x014e }
        L_0x00e2:
            monitor-exit(r7)     // Catch:{ all -> 0x014e }
            return r6
        L_0x00e4:
            r0 = move-exception
            r11 = 1
            long r16 = java.lang.System.nanoTime()     // Catch:{ ExecutionException -> 0x0107, TimeoutException -> 0x00ef }
            long r12 = r14 - r16
            goto L_0x00b9
        L_0x00ed:
            r0 = move-exception
            goto L_0x012c
        L_0x00ef:
            r0 = move-exception
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00ed }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ed }
            r6.<init>()     // Catch:{ all -> 0x00ed }
            r6.append(r2)     // Catch:{ all -> 0x00ed }
            java.lang.String r12 = " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread"
            r6.append(r12)     // Catch:{ all -> 0x00ed }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00ed }
            r5.<init>(r6, r0)     // Catch:{ all -> 0x00ed }
            throw r5     // Catch:{ all -> 0x00ed }
        L_0x0107:
            r0 = move-exception
            java.lang.Throwable r5 = r0.getCause()     // Catch:{ all -> 0x00ed }
            boolean r5 = r5 instanceof java.lang.RuntimeException     // Catch:{ all -> 0x00ed }
            if (r5 != 0) goto L_0x0125
            java.lang.Throwable r5 = r0.getCause()     // Catch:{ all -> 0x00ed }
            boolean r5 = r5 instanceof java.lang.Error     // Catch:{ all -> 0x00ed }
            if (r5 == 0) goto L_0x011f
            java.lang.Throwable r5 = r0.getCause()     // Catch:{ all -> 0x00ed }
            java.lang.Error r5 = (java.lang.Error) r5     // Catch:{ all -> 0x00ed }
            throw r5     // Catch:{ all -> 0x00ed }
        L_0x011f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00ed }
            r5.<init>(r0)     // Catch:{ all -> 0x00ed }
            throw r5     // Catch:{ all -> 0x00ed }
        L_0x0125:
            java.lang.Throwable r5 = r0.getCause()     // Catch:{ all -> 0x00ed }
            java.lang.RuntimeException r5 = (java.lang.RuntimeException) r5     // Catch:{ all -> 0x00ed }
            throw r5     // Catch:{ all -> 0x00ed }
        L_0x012c:
            if (r11 == 0) goto L_0x0135
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x014e }
            r5.interrupt()     // Catch:{ all -> 0x014e }
        L_0x0135:
            throw r0     // Catch:{ all -> 0x014e }
        L_0x0137:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x014e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x014e }
            r5.<init>()     // Catch:{ all -> 0x014e }
            r5.append(r2)     // Catch:{ all -> 0x014e }
            java.lang.String r6 = " is shutting down"
            r5.append(r6)     // Catch:{ all -> 0x014e }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x014e }
            r0.<init>(r5)     // Catch:{ all -> 0x014e }
            throw r0     // Catch:{ all -> 0x014e }
        L_0x014e:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x014e }
            goto L_0x0152
        L_0x0151:
            throw r0
        L_0x0152:
            goto L_0x0151
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.location.LocationManagerCompat.registerGnssStatusCallback(android.location.LocationManager, android.os.Handler, java.util.concurrent.Executor, androidx.core.location.GnssStatusCompat$Callback):boolean");
    }

    public static void unregisterGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            SimpleArrayMap<Object, Object> simpleArrayMap = sGnssStatusListeners;
            synchronized (simpleArrayMap) {
                GnssStatusTransport transport = (GnssStatusTransport) simpleArrayMap.remove(callback);
                if (transport != null) {
                    locationManager.unregisterGnssStatusCallback(transport);
                }
            }
        } else if (Build.VERSION.SDK_INT >= 24) {
            SimpleArrayMap<Object, Object> simpleArrayMap2 = sGnssStatusListeners;
            synchronized (simpleArrayMap2) {
                PreRGnssStatusTransport transport2 = (PreRGnssStatusTransport) simpleArrayMap2.remove(callback);
                if (transport2 != null) {
                    transport2.unregister();
                    locationManager.unregisterGnssStatusCallback(transport2);
                }
            }
        } else {
            SimpleArrayMap<Object, Object> simpleArrayMap3 = sGnssStatusListeners;
            synchronized (simpleArrayMap3) {
                GpsStatusTransport transport3 = (GpsStatusTransport) simpleArrayMap3.remove(callback);
                if (transport3 != null) {
                    transport3.unregister();
                    locationManager.removeGpsStatusListener(transport3);
                }
            }
        }
    }

    private LocationManagerCompat() {
    }

    private static class GnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;

        GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void onStarted() {
            this.mCallback.onStarted();
        }

        public void onStopped() {
            this.mCallback.onStopped();
        }

        public void onFirstFix(int ttffMillis) {
            this.mCallback.onFirstFix(ttffMillis);
        }

        public void onSatelliteStatusChanged(GnssStatus status) {
            this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(status));
        }
    }

    private static class PreRGnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;

        PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            boolean z = true;
            Preconditions.checkArgument(executor != null, "invalid null executor");
            if (this.mExecutor != null) {
                z = false;
            }
            Preconditions.checkState(z);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        public void onStarted() {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onStarted();
                        }
                    }
                });
            }
        }

        public void onStopped() {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onStopped();
                        }
                    }
                });
            }
        }

        public void onFirstFix(final int ttffMillis) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onFirstFix(ttffMillis);
                        }
                    }
                });
            }
        }

        public void onSatelliteStatusChanged(final GnssStatus status) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                executor.execute(new Runnable() {
                    public void run() {
                        if (PreRGnssStatusTransport.this.mExecutor == executor) {
                            PreRGnssStatusTransport.this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(status));
                        }
                    }
                });
            }
        }
    }

    private static class GpsStatusTransport implements GpsStatus.Listener {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;
        private final LocationManager mLocationManager;

        GpsStatusTransport(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mLocationManager = locationManager;
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            Preconditions.checkState(this.mExecutor == null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        public void onGpsStatusChanged(int event) {
            final Executor executor = this.mExecutor;
            if (executor != null) {
                switch (event) {
                    case 1:
                        executor.execute(new Runnable() {
                            public void run() {
                                if (GpsStatusTransport.this.mExecutor == executor) {
                                    GpsStatusTransport.this.mCallback.onStarted();
                                }
                            }
                        });
                        return;
                    case 2:
                        executor.execute(new Runnable() {
                            public void run() {
                                if (GpsStatusTransport.this.mExecutor == executor) {
                                    GpsStatusTransport.this.mCallback.onStopped();
                                }
                            }
                        });
                        return;
                    case 3:
                        GpsStatus gpsStatus = this.mLocationManager.getGpsStatus((GpsStatus) null);
                        if (gpsStatus != null) {
                            final int ttff = gpsStatus.getTimeToFirstFix();
                            executor.execute(new Runnable() {
                                public void run() {
                                    if (GpsStatusTransport.this.mExecutor == executor) {
                                        GpsStatusTransport.this.mCallback.onFirstFix(ttff);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    case 4:
                        GpsStatus gpsStatus2 = this.mLocationManager.getGpsStatus((GpsStatus) null);
                        if (gpsStatus2 != null) {
                            final GnssStatusCompat gnssStatus = GnssStatusCompat.wrap(gpsStatus2);
                            executor.execute(new Runnable() {
                                public void run() {
                                    if (GpsStatusTransport.this.mExecutor == executor) {
                                        GpsStatusTransport.this.mCallback.onSatelliteStatusChanged(gnssStatus);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static class InlineHandlerExecutor implements Executor {
        private final Handler mHandler;

        InlineHandlerExecutor(Handler handler) {
            this.mHandler = (Handler) Preconditions.checkNotNull(handler);
        }

        public void execute(Runnable command) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                command.run();
            } else if (!this.mHandler.post((Runnable) Preconditions.checkNotNull(command))) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }

    private static class Api28Impl {
        private Api28Impl() {
        }

        public static boolean isLocationEnabled(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }
    }
}
