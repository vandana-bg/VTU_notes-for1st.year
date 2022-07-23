package com.google.firebase.heartbeatinfo;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.google.firebase.heartbeatinfo.-$$Lambda$DefaultHeartBeatInfo$1Q21IbGcrjKFfJt9vQbGxqGVgmM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DefaultHeartBeatInfo$1Q21IbGcrjKFfJt9vQbGxqGVgmM implements ThreadFactory {
    public static final /* synthetic */ $$Lambda$DefaultHeartBeatInfo$1Q21IbGcrjKFfJt9vQbGxqGVgmM INSTANCE = new $$Lambda$DefaultHeartBeatInfo$1Q21IbGcrjKFfJt9vQbGxqGVgmM();

    private /* synthetic */ $$Lambda$DefaultHeartBeatInfo$1Q21IbGcrjKFfJt9vQbGxqGVgmM() {
    }

    public final Thread newThread(Runnable runnable) {
        return DefaultHeartBeatInfo.lambda$static$0(runnable);
    }
}
