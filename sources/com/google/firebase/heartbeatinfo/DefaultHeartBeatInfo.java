package com.google.firebase.heartbeatinfo;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Lazy;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultHeartBeatInfo implements HeartBeatInfo {
    private static final ThreadFactory THREAD_FACTORY = $$Lambda$DefaultHeartBeatInfo$1Q21IbGcrjKFfJt9vQbGxqGVgmM.INSTANCE;
    private final Executor backgroundExecutor;
    private final Set<HeartBeatConsumer> consumers;
    private Provider<HeartBeatInfoStorage> storageProvider;

    static /* synthetic */ Thread lambda$static$0(Runnable r) {
        return new Thread(r, "heartbeat-information-executor");
    }

    private DefaultHeartBeatInfo(Context context, Set<HeartBeatConsumer> consumers2) {
        this(new Lazy(new Provider(context) {
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final Object get() {
                return HeartBeatInfoStorage.getInstance(this.f$0);
            }
        }), consumers2, new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY));
    }

    DefaultHeartBeatInfo(Provider<HeartBeatInfoStorage> testStorage, Set<HeartBeatConsumer> consumers2, Executor executor) {
        this.storageProvider = testStorage;
        this.consumers = consumers2;
        this.backgroundExecutor = executor;
    }

    public HeartBeatInfo.HeartBeat getHeartBeatCode(String heartBeatTag) {
        long presentTime = System.currentTimeMillis();
        boolean shouldSendSdkHB = this.storageProvider.get().shouldSendSdkHeartBeat(heartBeatTag, presentTime);
        boolean shouldSendGlobalHB = this.storageProvider.get().shouldSendGlobalHeartBeat(presentTime);
        if (shouldSendSdkHB && shouldSendGlobalHB) {
            return HeartBeatInfo.HeartBeat.COMBINED;
        }
        if (shouldSendGlobalHB) {
            return HeartBeatInfo.HeartBeat.GLOBAL;
        }
        if (shouldSendSdkHB) {
            return HeartBeatInfo.HeartBeat.SDK;
        }
        return HeartBeatInfo.HeartBeat.NONE;
    }

    public Task<List<HeartBeatResult>> getAndClearStoredHeartBeatInfo() {
        return Tasks.call(this.backgroundExecutor, new Callable() {
            public final Object call() {
                return DefaultHeartBeatInfo.this.lambda$getAndClearStoredHeartBeatInfo$2$DefaultHeartBeatInfo();
            }
        });
    }

    public /* synthetic */ List lambda$getAndClearStoredHeartBeatInfo$2$DefaultHeartBeatInfo() throws Exception {
        HeartBeatInfo.HeartBeat heartBeat;
        ArrayList<HeartBeatResult> heartBeatResults = new ArrayList<>();
        HeartBeatInfoStorage storage = this.storageProvider.get();
        List<SdkHeartBeatResult> sdkHeartBeatResults = storage.getStoredHeartBeats(true);
        long lastGlobalHeartBeat = storage.getLastGlobalHeartBeat();
        for (SdkHeartBeatResult sdkHeartBeatResult : sdkHeartBeatResults) {
            boolean shouldSendGlobalHeartBeat = HeartBeatInfoStorage.isSameDateUtc(lastGlobalHeartBeat, sdkHeartBeatResult.getMillis());
            if (shouldSendGlobalHeartBeat) {
                heartBeat = HeartBeatInfo.HeartBeat.COMBINED;
            } else {
                heartBeat = HeartBeatInfo.HeartBeat.SDK;
            }
            if (shouldSendGlobalHeartBeat) {
                lastGlobalHeartBeat = sdkHeartBeatResult.getMillis();
            }
            heartBeatResults.add(HeartBeatResult.create(sdkHeartBeatResult.getSdkName(), sdkHeartBeatResult.getMillis(), heartBeat));
        }
        if (lastGlobalHeartBeat > 0) {
            storage.updateGlobalHeartBeat(lastGlobalHeartBeat);
        }
        return heartBeatResults;
    }

    public Task<Void> storeHeartBeatInfo(String heartBeatTag) {
        if (this.consumers.size() <= 0) {
            return Tasks.forResult(null);
        }
        return Tasks.call(this.backgroundExecutor, new Callable(heartBeatTag) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return DefaultHeartBeatInfo.this.lambda$storeHeartBeatInfo$3$DefaultHeartBeatInfo(this.f$1);
            }
        });
    }

    public /* synthetic */ Void lambda$storeHeartBeatInfo$3$DefaultHeartBeatInfo(String heartBeatTag) throws Exception {
        long presentTime = System.currentTimeMillis();
        if (!this.storageProvider.get().shouldSendSdkHeartBeat(heartBeatTag, presentTime)) {
            return null;
        }
        this.storageProvider.get().storeHeartBeatInformation(heartBeatTag, presentTime);
        return null;
    }

    public static Component<HeartBeatInfo> component() {
        return Component.builder(HeartBeatInfo.class).add(Dependency.required(Context.class)).add(Dependency.setOf(HeartBeatConsumer.class)).factory($$Lambda$DefaultHeartBeatInfo$uBe3Vi_40XEX86u78BnJDpBSTDI.INSTANCE).build();
    }

    static /* synthetic */ HeartBeatInfo lambda$component$4(ComponentContainer c) {
        return new DefaultHeartBeatInfo((Context) c.get(Context.class), c.setOf(HeartBeatConsumer.class));
    }
}
